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
import { OrderCard } from "./OrderCard";

interface Items {
  email: string;
  phone: string;
  total: number;
  totalPass: number[];
  totalTicket: number[];
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

  const totalSum = items.reduce((sum, item) => sum + item.total, 0);

  return (
    <>
    <OrderCard title="Reports panel">
      <h1>Total amount from selected time is: {totalSum}</h1>
    </OrderCard>
      <Table className="mt-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>#</TableHead>
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
                <TableCell>{item.total}</TableCell>
                <TableCell>
                  {Array.isArray(item.totalTicket) && item.totalTicket.length > 0 ? (
                    item.totalTicket.map((ticket, ticketIndex) => (
                      <div key={ticketIndex}>Ticket {ticketIndex}: {ticket}</div>
                    ))
                  ) : (
                    <div>No Tickets</div>
                  )}
                </TableCell>
                <TableCell>
                  {Array.isArray(item.totalPass) && item.totalPass.length > 0 ? (
                    item.totalPass.map((pass, passIndex) => (
                      <div key={passIndex}>Pass {passIndex}: {pass}</div>
                    ))
                  ) : (
                    <div>No Passes</div>
                  )}
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </>
  );
}
