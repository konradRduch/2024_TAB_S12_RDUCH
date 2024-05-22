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
  //priceList
  id: number;
  timeStart: string;
  timeEnd: string;
  ticketPrice: number;
  passPrice: GLfloat;
}

const Values = {
  price: 50,
  normal: 0, // ilosc biletów normalnych
  discount: 0, // ilosc biletow ulgowych
  rides: 1, //
  pricePerRide: 10,
  passType: "Day",
  email: "",
  phone: "",
  timeStart: "",
  timeEnd: "",
};

export function TicketComp() {
  const [items, setItems] = useState<Items[]>([]);
  const [values, setValues] = useState(Values);
  const [type, setType] = useState("Ticket");
  const [date, setDate] = useState<Date>();

  const fetchItems = () => {
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
        setValues((prevValues) => ({
          ...prevValues,
          price: lastItem.passPrice,
          pricePerRide: lastItem.ticketPrice,
        }));
        // Update state with all items
        setItems(sortedItems);
      })
      .catch((error) => console.error("Error:", error));
  };

  React.useEffect(() => {
    fetchItems();
  }, []);

  React.useEffect(() => {
    if (items.length > 0) {
      setValues((prevValues) => ({
        ...prevValues,
        price: items[0].passPrice,
        pricePerRide: items[0].ticketPrice,
      }));
    }
  }, [items]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (name === "email" || name === "phone") {
      setValues((prevValues) => ({
        ...prevValues,
        [name]: value,
      }));
    } else if (name === "date") {
      setDate(new Date(value));
      setValues((prevValues) => ({
        ...prevValues,
        timeStart: value + "T00:00:00", // Set time start to the beginning of the selected day
        timeEnd: value + "T23:59:59", // Set time end to the end of the selected day
      }));
    } else {
      setValues((prevValues) => ({
        ...prevValues,
        [name]: parseFloat(value),
      }));
    }
  };

  function Ticket() {
    return (
      <>
        <label>NORMAL TICKET</label>
        <Input
          type="number"
          name="normal"
          placeholder="Enter amount"
          min={0}
          value={values.normal}
          onChange={handleInputChange}
        />
        <label>DISCOUNTED TICKET</label>
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
        <label htmlFor="date">Start Date</label>
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
              onSelect={(selectedDate: Date | undefined) => {
                if (selectedDate) {
                  setDate(selectedDate);
                  setValues((prevValues) => ({
                    ...prevValues,
                    timeStart: format(selectedDate, "yyyy-MM-dd") + "T00:00:00", // Set time start to the beginning of the selected day
                    timeEnd: format(selectedDate, "yyyy-MM-dd") + "T23:59:59", // Set time end to the end of the selected day
                  }));
                }
              }}
              initialFocus
            />
          </PopoverContent>
        </Popover>
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
            }));
            Values.passType = value;
          }}
        >
          <SelectTrigger id="PassType">
            <SelectValue placeholder={Values.passType} />
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
        <label htmlFor="date">Start Date</label>
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
              onSelect={(selectedDate: Date | undefined) => {
                if (selectedDate) {
                  setDate(selectedDate);
                  setValues((prevValues) => ({
                    ...prevValues,
                    timeStart: format(selectedDate, "yyyy-MM-dd") + "T00:00:00", // Set time start to the beginning of the selected day
                    timeEnd: format(selectedDate, "yyyy-MM-dd") + "T23:59:59", // Set time end to the end of the selected day
                  }));
                }
              }}
              initialFocus
            />
          </PopoverContent>
        </Popover>
      </>
    );
  }

  const calculateTotal = () => {
    const { normal, discount, price } = values;
    let discountFactor = 1; // Default to no discount
    if (values.passType === "Day") {
      discountFactor = 1; // Day pass, no discount
    } else if (values.passType === "Week") {
      discountFactor = 7 * 0.6; // Week pass, 15% discount
    } else if (values.passType === "Month") {
      discountFactor = 30 * 0.4; // Month pass, 30% discount
    }
    const discountedNormalPrice = normal * price * discountFactor;
    const discountedDiscountPrice = discount * price * 0.5 * discountFactor;
    return discountedNormalPrice + discountedDiscountPrice;
  };

  const total = calculateTotal();
  const totalPerRides =
    values.normal * values.rides * values.pricePerRide +
    values.discount * values.rides * values.pricePerRide * 0.5;

  const calculateEndTime = (startTime: Date, passType: string) => {
    let endTime: Date;
    switch (passType) {
      case "Day":
        endTime = new Date(startTime);
        endTime.setHours(23, 59, 59); // End of the day
        break;
      case "Week":
        endTime = new Date(startTime);
        endTime.setDate(startTime.getDate() + 7); // End of the week
        endTime.setHours(23, 59, 59);
        break;
      case "Month":
        endTime = new Date(startTime);
        endTime.setMonth(startTime.getMonth() + 1); // End of the month
        endTime.setHours(23, 59, 59);
        break;
      default:
        endTime = new Date(startTime);
        break;
    }
    return endTime;
  };

  const handlePassSubmit = (event: any) => {
    event.preventDefault();

    const endTime = format(
      new Date(calculateEndTime(new Date(values.timeStart), values.passType)),
      "yyyy-MM-dd'T'HH:mm:ss"
    );

    const passData = {
      client: {
        phone: values.phone,
        email: values.email,
      },
      passDTO: {
        active: true,
        passTypeName: values.passType,
        timeStart: values.timeStart,
        timeEnd: endTime,
      },
      total: total,
      numberOfNormalPasses: values.normal,
      numberOfDiscountPasses: values.discount,
      priceList: {
        timeStart: items[0].timeStart,
        timeEnd: endTime,
        ticketPrice: items[0].ticketPrice,
        passPrice: items[0].passPrice,
      },
    };

    axios
      .post("http://localhost:8080/buyPasses", passData)
      .then((response) => {
        // Handle success
        const passIds = response.data; // Assuming the response contains the pass ID
        console.log("Pass data submitted:", response.data);
        alert(
          `Tickets successfully bought! Ticket ID(s): ${passIds.join(", ")}`
        );
      })
      .catch((error) => console.error("Error:", error));
  };

  const handleTicketSubmit = (event: any) => {
    event.preventDefault();

    const endTime = format(
      new Date(calculateEndTime(new Date(values.timeStart), values.passType)),
      "yyyy-MM-dd'T'HH:mm:ss"
    );
    const ticketData = {
      client: {
        email: values.email,
        phone: values.phone,
      },
      ticketDTO: {
        amountOfRides: values.rides,
        pricePerRide: values.pricePerRide,
        ticketTypeName: values.passType,
        timeStart: values.timeStart,
        timeEnd: endTime,
      },
      total: total,
      numberOfNormalPasses: values.normal,
      numberOfDiscountPasses: values.discount,
      priceList: {
        timeStart: items[0].timeStart,
        timeEnd: items[0].timeEnd,
        ticketPrice: items[0].ticketPrice,
        passPrice: items[0].passPrice,
      },
    };

    axios
      .post("http://localhost:8080/buyTickets", ticketData)
      .then((response) => {
        // Handle success
        const ticketIds = response.data;
        console.log("Ticket data submitted:", response.data);
        if (ticketIds === "Error") {
          alert("You entered wrong data! Try Again!");
        } else {
          alert(
            `Tickets successfully bought! pass ID(s): ${ticketIds.join(", ")}`
          );
        }
      })
      .catch((error) => console.error("Error:", error));
  };

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
          {items.length > 0 && (
            <TableRow>
              <TableCell>{items[0].passPrice}</TableCell>
              <TableCell>{items[0].ticketPrice}</TableCell>
              <TableCell>{items[0].timeStart}</TableCell>
              <TableCell>{items[0].timeEnd}</TableCell>
            </TableRow>
          )}
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
            <label>Email</label>
            <Input
              type="text"
              name="email"
              placeholder="Enter email"
              value={values.email}
              onChange={handleInputChange}
            />
            <label>Phone</label>
            <Input
              type="text"
              name="phone"
              placeholder="Enter phone number"
              value={values.phone}
              onChange={handleInputChange}
            />

            {type === "Ticket" ? <Ticket /> : <Pass />}
            <label htmlFor="Starting date">STARTING DATE</label>

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
            {type === "Pass" ? (
              <Button className="w-full" onClick={handlePassSubmit}>
                BUY
              </Button>
            ) : (
              <Button className="w-full" onClick={handleTicketSubmit}>
                BUY
              </Button>
            )}
          </CardFooter>
        </OrderCard>
      </div>
    </>
  );
}
