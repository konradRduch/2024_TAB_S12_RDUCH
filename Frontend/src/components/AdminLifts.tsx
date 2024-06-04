import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "./ui/button";
import axios from "axios";
import { useState, useEffect } from "react";
import { Card } from "./ui/card";
import { Input } from "./ui/input";

/**
 * Items interface represents a lift object.
 * @typedef {Object} Items
 * @property {number} id - The unique identifier of the lift.
 * @property {string} name - The name of the lift.
 * @property {boolean} active - The status of the lift, indicating if it's active.
 * @property {number} distance - The distance associated with the lift.
 */

interface Items {
  id: number;
  name: string;
  active: boolean;
  distance: number;
}

/**
 * AdminLiftsComp is a React functional component for managing the lifts.
 * @component
 * @returns {JSX.Element} - The JSX code for AdminLiftsComp component.
 */

export function AdminLiftsComp() {
  const [newHarmonogram, setNewHarmonogram] = useState({
    name: "",
    active: "",
    distance: "",
  });

  const [items, setItems] = useState<Items[]>([]);

  /**
   * Handles the form submission for adding a new lift.
   * @param {React.FormEvent<HTMLFormElement>} event - The form submission event.
   */
  const handleSubmit = (event: any) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/lifts/addLift", newHarmonogram)
      .then((response) => {
        setNewHarmonogram({
          name: "",
          active: "",
          distance: "",
        });
        fetchItems();
        window.location.reload();
      })
      .catch((error) => console.error("Error:", error));
  };

  /**
   * Fetches the list of lifts from the API.
   */
  const fetchItems = () => {
    axios
      .get("http://localhost:8080/lifts")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  /**
   * Handles the input change for the lift form.
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
   * Handles the deletion of a lift.
   * @param {number} id - The ID of the lift to be deleted.
   */
  const handleDelete = (id: number) => {
    axios
      .delete(`http://localhost:8080/lifts/deleteLift/${id}`)
      .then((response) => {
        fetchItems();
      })
      .catch((error) => console.error("Error:", error));
  };

  return (
    <>
      <p>Edit Harmonogram</p>

      <Table className="mt-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Distance</TableHead>
            <TableHead>Active</TableHead>
            <TableHead>Delete</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(items) &&
            items.map((item, index) => (
              <TableRow key={index}>
                <TableCell className="font-medium">{index + 1}</TableCell>
                <TableCell>{item.name}</TableCell>
                <TableCell>{item.distance}</TableCell>
                <TableCell>{item.active ? "YES" : "NO"}</TableCell>
                <TableCell>
                  <Button
                    className="w-full"
                    onClick={() => handleDelete(item.id)}
                  >
                    x
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>

      <Card className="mt-4">
        <div className="flex gap-4 p-10">
          <Input
            placeholder="Name"
            name="name"
            value={newHarmonogram.name}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Distance"
            name="distance"
            value={newHarmonogram.distance}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Active"
            name="active"
            value={newHarmonogram.active}
            onChange={handleInputChange}
          ></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
    </>
  );
}
