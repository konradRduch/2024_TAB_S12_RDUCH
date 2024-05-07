import { useState } from "react";
import { OrderCard } from "@/components/OrderCard";
import { Button } from "@/components/ui/button";
import { CardFooter } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
//import type { CheckedState } from '@radix-ui/react-checkbox';
import { format } from "date-fns";
import { Calendar as CalendarIcon } from "lucide-react";
import * as React from "react";
import { cn } from "@/lib/utils";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import axios from "axios";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

interface Items {
  id: number;
  timeStart: string;
  timeEnd: string;
  ticketPrice: number;
  passPrice: number;
}

const Values = {
  price: 50,
  normal: 0,
  discount: 0,
  rides: 1,
  pricePerRide: 10,
  passType: "Day",
};

export function TicketComp() {
  const [items, setItems] = useState<Items[]>([]);
  const [values, setValues] = useState(Values);
  const [type, setType] = useState("Ticket");
  const [date, setDate] = useState<Date>();

  const fetchItems = () => {
    axios
      .get("http://localhost:8080/priceLists")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  React.useEffect(() => {
    fetchItems();
  }, []);

  React.useEffect(() => {
    if (items.length > 0) {
      setValues((prevValues) => ({
        ...prevValues,
        price: items[0].ticketPrice,
        pricePerRide: items[0].passPrice,
      }));
    }
  }, [items]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setValues((prevValues) => ({
      ...prevValues,
      [name]: parseFloat(value), // Parse value to float
    }));
  };

  function Ticket() {
    return (
      <>
        <label>NORMAL</label>
        <Input
          type="number"
          name="normal"
          placeholder="Enter amount"
          min={0}
          value={values.normal}
          onChange={handleInputChange}
        />
        <label>DISCOUNT</label>
        <Input
          type="number"
          name="discount"
          placeholder="Enter price"
          min={0}
          value={values.discount}
          onChange={handleInputChange}
        />
        <label htmlFor="Rides">Amount of Rides</label>
        <Input
          type="number"
          name="rides"
          placeholder="Enter price"
          min={0}
          value={values.rides}
          onChange={handleInputChange}
        />
      </>
    );
  }

  function Pass() {
    return (
      <>
        <Select
          onValueChange={(value: string) => {
            setValues((prevValues) => ({
              ...prevValues,
              passType: value,
            }))
            Values.passType = value
          }}
        >
          <SelectTrigger id="PassType">
            <SelectValue placeholder={Values.passType}/>
          </SelectTrigger>
          <SelectContent position="popper">
            <SelectItem value="Day">Day</SelectItem>
            <SelectItem value="Week">Week</SelectItem>
            <SelectItem value="Month">Month</SelectItem>
          </SelectContent>
        </Select>
        <label>NORMAL</label>
        <Input
          type="number"
          name="normal"
          placeholder="Enter amount"
          min={0}
          value={values.normal}
          onChange={handleInputChange}
        />
        <label>DISCOUNT</label>
        <Input
          type="number"
          name="discount"
          placeholder="Enter price"
          min={0}
          value={values.discount}
          onChange={handleInputChange}
        />
      </>
    );
  }

  const calculateTotal = () => {
    const { normal, discount, price } = values;
    let discountFactor = 1; // Default to no discount
    if (values.passType === "Day") {
      discountFactor = 1; // Day pass, no discount
    } else if (values.passType === "Week") {
      discountFactor = 0.85; // Week pass, 15% discount
    } else if (values.passType === "Month") {
      discountFactor = 0.7; // Month pass, 30% discount
    }
    return normal * price * discountFactor + discount * price * 0.5 * discountFactor;
  };

  const total = calculateTotal();
  const totalPerRides =
    values.normal * values.rides * values.pricePerRide +
    values.discount * values.rides * values.pricePerRide * 0.5;

  return (
    <>
      <Table className="my-12 w-1/2 mx-auto">
        <TableHeader>
          <TableRow>
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
                <TableCell>{item.passPrice}</TableCell>
                <TableCell>{item.ticketPrice}</TableCell>
                <TableCell>{item.timeStart}</TableCell>
                <TableCell>{item.timeEnd}</TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>

      <div className="flex gap-4 flex-col lg:flex-row">
        <OrderCard
          title="BUY TICKET"
          desc="check out our wide variety of tickets"
        >
          <div className="grid gap-4">
            <Select onValueChange={(value: string) => setType(value)}>
              <SelectTrigger id="category">
                <SelectValue placeholder="Ticket" />
              </SelectTrigger>
              <SelectContent position="popper">
                <SelectItem value="Ticket">Ticket</SelectItem>
                <SelectItem value="Pass">Pass</SelectItem>
              </SelectContent>
            </Select>

            {type === "Ticket" ? <Ticket /> : <Pass />}
            <label htmlFor="Starting date">STARTING DATE</label>

            <Popover>
              <PopoverTrigger asChild>
                <Button
                  variant={"outline"}
                  className={cn(
                    "w-[280px] justify-start text-left font-normal",
                    !date && "text-muted-foreground"
                  )}
                >
                  <CalendarIcon className="mr-2 h-4 w-4" />
                  {date ? format(date, "PPP") : <span>Pick a date</span>}
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-auto p-0">
                <Calendar
                  mode="single"
                  selected={date}
                  onSelect={setDate}
                  initialFocus
                />
              </PopoverContent>
            </Popover>

            <div className="items-top flex space-x-2 mt-2">
              <Checkbox id="terms1" />
              <div className="grid gap-1.5 leading-none">
                <label
                  htmlFor="terms1"
                  className="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
                >
                  Discount?
                </label>
                <p className="text-sm text-muted-foreground">
                  You agree to our Terms of Service and Privacy Policy.
                </p>
              </div>
            </div>
          </div>
        </OrderCard>
        <OrderCard title="TOTAL" desc="Amount in pln">
          <div className="text-center font-bold">
            {type === "Pass" ? total.toFixed(2) : totalPerRides.toFixed(2)} PLN
          </div>
          <CardFooter className="flex justify-center">
            <Button className="w-full">BUY</Button>
          </CardFooter>
        </OrderCard>
      </div>
    </>
  );
}
