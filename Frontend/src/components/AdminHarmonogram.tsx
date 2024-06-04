import { Harmonogram } from "@/components/Harmonogram";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Button } from "./ui/button";
import axios from "axios";
import { useState, useEffect } from "react";

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

/**
 * Lift interface represents a ski lift object.
 * @typedef {Object} Lift
 * @property {number} id - The unique identifier of the lift.
 * @property {string} name - The name of the lift.
 * @property {boolean} active - The status of the lift, indicating if it's active.
 * @property {number} distance - The distance associated with the lift.
 */

interface Lift {
  id: number;
  name: string;
  active: boolean;
  distance: number;
}

/**
 * AdminHarmonogramComp is a React functional component for managing the harmonogram of ski lifts.
 * @component
 * @returns {JSX.Element} - The JSX code for AdminHarmonogramComp component.
 */

export function AdminHarmonogramComp() {
  const [newHarmonogram, setNewHarmonogram] = useState({
    open: "",
    close: "",
    liftId: "",
  });
  const [items, setItems] = useState([]);
  const [lifts, setLifts] = useState<Lift[]>([]);

  /**
   * Handles the form submission for adding a new harmonogram.
   * @param {React.FormEvent<HTMLFormElement>} event - The form submission event.
   */
  const handleSubmit = (event: any) => {
    event.preventDefault();

    const formattedHarmonogram = {
      ...newHarmonogram,
      open: `${new Date().toISOString().split("T")[0]}T${newHarmonogram.open}`,
      close: `${new Date().toISOString().split("T")[0]}T${newHarmonogram.close}`,
    };

    axios
      .post(
        "http://localhost:8080/skiSchedules/addSkiScheduleRequest",
        formattedHarmonogram
      )
      .then((response) => {
        setNewHarmonogram({
          open: "",
          close: "",
          liftId: "",
        });
        fetchItems();
      })
      .catch((error) => console.error("Error:", error));
  };

  /**
   * Fetches the harmonogram items and active lifts from the API.
   */
  const fetchItems = () => {
    axios
      .get("http://localhost:8080/skiSchedules/dto")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));

    axios
      .get("http://localhost:8080/lifts")
      .then((response) =>
        setLifts(response.data.filter((lift: Lift) => lift.active))
      )
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  /**
   * Handles the input change for the harmonogram form.
   * @param {React.ChangeEvent<HTMLInputElement>} e - The input change event.
   */
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setNewHarmonogram((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  /**
   * Handles the selection of a lift from the dropdown.
   * @param {string} liftId - The ID of the selected lift.
   */
  const handleLiftSelect = (liftId: string) => {
    setNewHarmonogram((prevState) => ({
      ...prevState,
      liftId: liftId,
    }));
  };

  return (
    <>
      <p>Edit Harmonogram</p>
      <Harmonogram admin={true} />
      <Card className="mt-4">
        <div className="flex gap-4 p-10">
          <Select onValueChange={handleLiftSelect}>
            <SelectTrigger id="lift">
              <SelectValue placeholder="Select Lift" />
            </SelectTrigger>
            <SelectContent position="popper">
              {lifts.map((lift) => (
                <SelectItem key={lift.id} value={lift.id.toString()}>
                  {lift.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
          <Input
            placeholder="Open (HH:MM)"
            name="open"
            value={newHarmonogram.open}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Close (HH:MM)"
            name="close"
            value={newHarmonogram.close}
            onChange={handleInputChange}
          ></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
    </>
  );
}
