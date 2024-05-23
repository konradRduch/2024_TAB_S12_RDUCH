import axios from "axios";
import { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "react-day-picker";

interface Items {
  email: string;
  phone: string;
  orderTotal: number;
  passTotal: number[];
  ticketTotal: number[];
}

export function AdminReportComp() {
  const [items, setItems] = useState<Items[]>([]);

  const fetchItems = () => {
    axios
      .get("http://localhost:8080/reports")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  return (
    <>
      <Table className="mt-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>Email</TableHead>
            <TableHead>Phone</TableHead>
            <TableHead>Total</TableHead>
            <TableHead>Tickets</TableHead>
            <TableHead>Passes</TableHead>

          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(items) &&
            items.map((item, index) => (
              <TableRow key={index}>
                <TableCell className="font-medium">{index + 1}</TableCell>
                <TableCell>{item.email}</TableCell>
                <TableCell>{item.phone}</TableCell>
                <TableCell>{item.orderTotal}</TableCell>
                <TableCell>{item.ticketTotal}</TableCell>
                <TableCell>{item.passTotal}</TableCell>

              </TableRow>
            ))}
        </TableBody>
      </Table>
    </>
  );
}
