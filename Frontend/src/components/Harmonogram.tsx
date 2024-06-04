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

/**
 * Items interface represents a schedule item object.
 * @typedef {Object} Items
 * @property {number} id - The unique identifier of the schedule item.
 * @property {string} open - The opening time of the schedule.
 * @property {string} close - The closing time of the schedule.
 * @property {number} Liftid - The ID of the lift associated with the schedule.
 * @property {string} liftName - The name of the lift.
 * @property {boolean} active - Indicates if the lift is active.
 * @property {number} distance - The distance covered by the lift.
 */

interface Items {
  id: number;
  open: string;
  close: string;
  Liftid: number;
  liftName: string;
  active: boolean;
  distance: number;
}

/**
 * Props interface represents the props passed to the Harmonogram component.
 * @typedef {Object} Props
 * @property {boolean} [admin] - Indicates if the current user is an admin.
 */

type Props = {
  admin?: boolean | undefined;
};

/**
 * Harmonogram is a React functional component for displaying a schedule table.
 * @component
 * @param {Props} props - The props passed to the component.
 * @returns {JSX.Element} - The JSX code for Harmonogram component.
 */

export function Harmonogram({ admin }: Props) {
  const [items, setItems] = useState<Items[]>([]);

  /**
   * Fetches the schedule items from the API.
   */
  const fetchItems = () => {
    axios
      .get("http://localhost:8080/skiSchedules/dto")
      .then((response) => {
        setItems(response.data);
      })
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  /**
   * Handles the deletion of a schedule item.
   * @param {number} id - The unique identifier of the schedule item to delete.
   */
  const handleDelete = (id: number) => {
    axios
      .delete(`http://localhost:8080/skiSchedules/${id}`)
      .then((response) => {
        fetchItems();
      })
      .catch((error) => console.error("Error:", error));
  };
  return (
    <Table className="mt-12 w-1/2 mx-auto">
      <TableHeader>
        <TableRow>
          <TableHead>ID</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Distance</TableHead>
          <TableHead>Start</TableHead>
          <TableHead>End</TableHead>
          {admin ? <TableHead>Active</TableHead> : undefined}
          {admin ? <TableHead>Delete</TableHead> : undefined}
        </TableRow>
      </TableHeader>
      <TableBody>
        {admin ? (
          <>
            {Array.isArray(items) &&
              items.map(
                (item, index) =>
                  item.active && ( // Only render if item is active
                    <TableRow key={index}>
                      <TableCell className="font-medium">{index + 1}</TableCell>
                      <TableCell>{item.liftName}</TableCell>
                      <TableCell>{item.distance}</TableCell>
                      <TableCell>{item.open}</TableCell>
                      <TableCell>{item.close}</TableCell>
                      {admin && (
                        <TableCell>{item.active ? "YES" : "NO"}</TableCell>
                      )}
                      {admin && (
                        <TableCell>
                          <Button
                            className="w-full"
                            onClick={() => handleDelete(item.id)}
                          >
                            x
                          </Button>
                        </TableCell>
                      )}
                    </TableRow>
                  )
              )}
          </>
        ) : (
          <>
            {Array.isArray(items) &&
              items.map((item, index) => (
                <TableRow key={index}>
                  {item.active ? (
                    <>
                      <TableCell className="font-medium">{index + 1}</TableCell>
                      <TableCell>{item.liftName}</TableCell>
                      <TableCell>{item.distance}</TableCell>
                      <TableCell>{item.open}</TableCell>
                      <TableCell>{item.close}</TableCell>
                    </>
                  ) : undefined}
                </TableRow>
              ))}
          </>
        )}
      </TableBody>
    </Table>
  );
}
