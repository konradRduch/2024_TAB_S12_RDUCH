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

const Values = {
  price: 50,
  normal: 0,
  discount: 0,
  rides: 1,
  pricePerRide: 10,
};

export function TicketComp() {
  const [values, setValues] = useState(Values);
  const [type, setType] = useState("Ticket");
  const [date, setDate] = React.useState<Date>();

  const total =
    values.normal * values.price + values.discount * values.price * 0.5;
  const totalPerRides =
    values.normal * values.rides * values.pricePerRide +
    values.discount * values.rides * values.pricePerRide * 0.5;

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setValues((values) => ({
      ...values,
      [name]: value,
    }));
  };

  // const handleCheckbox = (value : boolean) => {
  //   setValues(
  //     (values) => ({
  //       ...values,
  //       discount: value,
  //   })
  //   )
  // }

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
        <Select>
          <SelectTrigger id="PassType">
            <SelectValue placeholder="Day" />
          </SelectTrigger>
          <SelectContent position="popper">
            <SelectItem value="Normal">Day</SelectItem>
            <SelectItem value="Senior">Week</SelectItem>
            <SelectItem value="Kid">Month</SelectItem>
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

  return (
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
  );
}
