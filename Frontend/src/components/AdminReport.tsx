import axios from "axios";
import { useEffect, useState } from "react";
import { format } from "date-fns";
import { Calendar as CalendarIcon } from "lucide-react";
import { Button } from "./ui/button";
import { Line } from "react-chartjs-2";
import "chart.js/auto";
import { Popover, PopoverContent, PopoverTrigger } from "./ui/popover";
import { Calendar } from "@/components/ui/calendar";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { OrderCard } from "./OrderCard";

/**
 * Items interface represents a report item object.
 * @typedef {Object} Items
 * @property {string} email - The email associated with the report item.
 * @property {string} phone - The phone number associated with the report item.
 * @property {number} total - The total income for the report item.
 * @property {number[]} totalPass - The total pass prices for the report item.
 * @property {number[]} totalTicket - The total ticket prices for the report item.
 * @property {string} dateTime - The date and time of the report item.
 */

interface Items {
  email: string;
  phone: string;
  total: number;
  totalPass: number[];
  totalTicket: number[];
  dateTime: string;
}

/**
 * AdminReportComp is a React functional component for generating and displaying reports.
 * @component
 * @returns {JSX.Element} - The JSX code for AdminReportComp component.
 */

export function AdminReportComp() {
  const [items, setItems] = useState<Items[]>([]);
  const [startDate, setStartDate] = useState<Date>();
  const [endDate, setEndDate] = useState<Date>();

  /**
   * Fetches the report items from the API for a given date range.
   * @param {Date} start - The start date of the range.
   * @param {Date} end - The end date of the range.
   */
  const fetchItems = (start: Date, end: Date) => {
    const startISO = format(start, "yyyy-MM-dd'T'00:00:00");
    const endISO = format(end, "yyyy-MM-dd'T'23:59:59");
    axios
      .get(
        `http://localhost:8080/reports/byDate?startDate=${startISO}&endDate=${endISO}`
      )
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  /**
   * Handles the fetch action when the user submits the date range.
   */
  const handleFetch = () => {
    fetchItems(startDate!, endDate!);
  };

  const chartData = {
    labels: items.map((item) => format(new Date(item.dateTime), "dd-MM-yyyy")),
    datasets: [
      {
        label: " Income",
        data: items.map((item) => item.total),
        borderColor: "rgba(2, 192, 22, 1)",
        backgroundColor: "rgba(2, 192, 22, 0.3)",
        fill: true,
      },
    ],
  };

  const chartOptions = {
    scales: {
      x: {
        ticks: {
          maxTicksLimit: 10,
          autoSkip: true,
          maxRotation: 90,
          minRotation: 30,
        },
      },
    },
  };

  const totalSum = items.reduce((sum, item) => sum + item.total, 0);

  return (
    <>
      <div className="flex gap-12">
        <div className="w-1/2 mt-12">
          <OrderCard title="Reports panel">
            <h1>Total amount from selected time is: {totalSum}</h1>

            <div className="flex flex-col gap-4 mt-8">
              <label>
                Start Date:
                <Popover>
                  <PopoverTrigger asChild>
                    <Button
                      variant={"outline"}
                      className="w-full justify-start text-left font-normal"
                    >
                      <CalendarIcon className="mr-2 h-4 w-4" />
                      {startDate ? (
                        format(startDate, "PPP")
                      ) : (
                        <span>Pick a date</span>
                      )}
                    </Button>
                  </PopoverTrigger>
                  <PopoverContent className="w-auto p-0">
                    <Calendar
                      mode="single"
                      selected={startDate}
                      onSelect={(selectedDate: Date | undefined) => {
                        if (selectedDate) {
                          setStartDate(selectedDate);
                        }
                      }}
                      initialFocus
                    />
                  </PopoverContent>
                </Popover>
              </label>
              <label>
                End Date:
                <Popover>
                  <PopoverTrigger asChild>
                    <Button
                      variant={"outline"}
                      className="w-full justify-start text-left font-normal"
                    >
                      <CalendarIcon className="mr-2 h-4 w-4" />
                      {endDate ? (
                        format(endDate, "PPP")
                      ) : (
                        <span>Pick a date</span>
                      )}
                    </Button>
                  </PopoverTrigger>
                  <PopoverContent className="w-auto p-0">
                    <Calendar
                      mode="single"
                      selected={endDate}
                      onSelect={(selectedDate: Date | undefined) => {
                        if (selectedDate) {
                          setEndDate(selectedDate);
                        }
                      }}
                      initialFocus
                    />
                  </PopoverContent>
                </Popover>
              </label>
              <Button onClick={handleFetch}>Fetch Reports</Button>
            </div>
          </OrderCard>
        </div>
        <div className="mt-12 w-full">
          <Line data={chartData} options={chartOptions} />
        </div>
      </div>
      <Table className="mt-12 w-3/4 mx-auto">
        <TableHeader>
          <TableRow>
            <TableHead>#</TableHead>
            <TableHead>Date</TableHead>
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
                <TableCell>
                  {format(new Date(item.dateTime), "dd-MM-yyyy")}
                </TableCell>
                <TableCell>{item.email}</TableCell>
                <TableCell>{item.phone}</TableCell>
                <TableCell>{item.total}</TableCell>
                <TableCell>
                  {Array.isArray(item.totalTicket) &&
                  item.totalTicket.length > 0 ? (
                    item.totalTicket.map((ticket, ticketIndex) => (
                      <div key={ticketIndex}>
                        Ticket {ticketIndex + 1}: {ticket}
                      </div>
                    ))
                  ) : (
                    <div>No Tickets</div>
                  )}
                </TableCell>
                <TableCell>
                  {Array.isArray(item.totalPass) &&
                  item.totalPass.length > 0 ? (
                    item.totalPass.map((pass, passIndex) => (
                      <div key={passIndex}>
                        Pass {passIndex + 1}: {pass}
                      </div>
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
