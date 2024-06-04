import axios from "axios";
import { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "./ui/button";
import { Card } from "./ui/card";
import { Input } from "./ui/input";

/**
 * Items interface represents a price list item object.
 * @typedef {Object} Items
 * @property {number} id - The unique identifier of the price list item.
 * @property {string} timeStart - The start time of the price validity.
 * @property {string} timeEnd - The end time of the price validity.
 * @property {number} ticketPrice - The price of a ticket.
 * @property {boolean} passPrice - Indicates if it's a pass price.
 */

interface Items {
  id: number;
  timeStart: string;
  timeEnd: string;
  ticketPrice: number;
  passPrice: boolean;
}

/**
 * AdminPricesComp is a React functional component for managing price lists.
 * @component
 * @returns {JSX.Element} - The JSX code for AdminPricesComp component.
 */

export function AdminPricesComp() {
  const [newHarmonogram, setNewHarmonogram] = useState({
    timeStart: "",
    timeEnd: "",
    ticketPrice: "",
    passPrice: "",
  });
  const [items, setItems] = useState<Items[]>([]);
  const [items_actual, setItems_actual] = useState<Items[]>([]);

  /**
   * Handles the form submission for adding a new price list item.
   * @param {React.FormEvent<HTMLFormElement>} event - The form submission event.
   */
  const handleSubmit = (event: any) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/priceLists/addPriceList", newHarmonogram)
      .then((response) => {
        setNewHarmonogram({
          timeStart: "",
          timeEnd: "",
          ticketPrice: "",
          passPrice: "",
        });
        fetchItems();
        window.location.reload();
      })
      .catch((error) => console.error("Error:", error));
  };

  /**
   * Fetches the list of price list items from the API.
   */
  const fetchItems = () => {
    axios
      .get("http://localhost:8080/priceLists")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  /**
   * Fetches the actual list of price list items from the API and updates state.
   */
  const fetchItems_actuall = () => {
    axios
      .get("http://localhost:8080/priceLists/actual")
      .then((response) => {
        // Sort items by some criteria, e.g., ID in descending order
        const sortedItems = response.data.sort(
          (a: Items, b: Items) => b.id - a.id
        );
        // Get the last item from the sorted list
        const lastItem = sortedItems[0];
        // Update state with the last pass price and ticket price
        setItems_actual((prevValues) => ({
          ...prevValues,
          price: lastItem.passPrice,
          pricePerRide: lastItem.ticketPrice,
        }));
        // Update state with all items
        setItems_actual(sortedItems);
      })
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
    fetchItems_actuall();
  }, []);

  /**
   * Handles the input change for the price list form.
   * @param {React.ChangeEvent<HTMLInputElement>} e - The input change event.
   */
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setNewHarmonogram((Harmonogram) => ({
      ...Harmonogram,
      [name]: value,
    }));
  };

  return (
    <>
      <p>Prices</p>
      <Card className="mt-4">
        <div className="flex gap-4 p-10">
          <Input
            placeholder="Pass price"
            name="passPrice"
            value={newHarmonogram.passPrice}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Ticket price"
            name="ticketPrice"
            value={newHarmonogram.ticketPrice}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Time start"
            name="timeStart"
            value={newHarmonogram.timeStart}
            onChange={handleInputChange}
          ></Input>
          <Input
            placeholder="Time end"
            name="timeEnd"
            value={newHarmonogram.timeEnd}
            onChange={handleInputChange}
          ></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
      <h1 className="mt-10">ACTUALL PRICESS:</h1>
      <Table className="mt-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Pass Price</TableHead>
            <TableHead>Ticket Price</TableHead>
            <TableHead>Time Start</TableHead>
            <TableHead>TimeEnd</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(items_actual) && items_actual.length > 0 && (
            <TableRow>
              <TableCell className="font-medium">
                {items_actual[0].id}
              </TableCell>
              <TableCell>{items_actual[0].passPrice}</TableCell>
              <TableCell>{items_actual[0].ticketPrice}</TableCell>
              <TableCell>{items_actual[0].timeStart}</TableCell>
              <TableCell>{items_actual[0].timeEnd}</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>

      <h1 className="mt-10">ALL PRICESS:</h1>
      <Table className="mt-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Pass Price</TableHead>
            <TableHead>Ticket Price</TableHead>
            <TableHead>Time Start</TableHead>
            <TableHead>TimeEnd</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(items) &&
            items.map((item, index) => (
              <TableRow key={index}>
                <TableCell className="font-medium">{item.id}</TableCell>
                <TableCell>{item.passPrice}</TableCell>
                <TableCell>{item.ticketPrice}</TableCell>
                <TableCell>{item.timeStart}</TableCell>
                <TableCell>{item.timeEnd}</TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </>
  );
}
