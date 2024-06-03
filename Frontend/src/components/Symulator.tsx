import { useState, useEffect } from "react";
import { OrderCard } from "./OrderCard";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import axios from "axios";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select";
import { differenceInMinutes, parseISO } from 'date-fns';

interface SymulatorTicket {
  liftId: number | string;
  ticketId?: number | string; // Make ticketId optional
  passId?: never; // Ensure passId is not present in SymulatorTicket
}

interface SymulatorPass {
  liftId: number | string;
  passId?: number | string; // Make passId optional
  ticketId?: never; // Ensure ticketId is not present in SymulatorPass
}

interface SimulationStats {
  active: boolean;
  totalDistance: number;
  timeStart: string;
  timeEnd: string;
  descentsNumber: number;
  amountOfRidesLeft: number;
}

export function SymulatorComp() {
  const [formData, setFormData] = useState<SymulatorTicket | SymulatorPass>({ liftId: "" });
  const [stats, setStats] = useState<SimulationStats | null>(null);
  const [type, setType] = useState("Ticket");
  const [timeLeft, setTimeLeft] = useState<string | null>(null);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSimulationSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
  
    try {
      let endpoint = "";
      if ("ticketId" in formData && formData.ticketId) {
        endpoint = "liftTicket";
      } else if ("passId" in formData && formData.passId) {
        endpoint = "liftPass";
      } else {
        throw new Error("No ticket or pass ID provided.");
      }
  
      await axios.post(
        `http://localhost:8080/${endpoint}/bounce${endpoint}`,
        formData
      );
  
      const response = await axios.get(
        `http://localhost:8080/${endpoint}/summary`,
        {
          params: formData,
        }
      );
  
      setStats(response.data);
      setFormData({ liftId: "" });
    } catch (error) {
      console.error("Error:", error);
      // Display alert
      alert("Error: Incorrect data entered.");
      // Clear the form data
      setFormData({ liftId: "" });
    }
  };

  useEffect(() => {
    if (stats?.timeEnd) {
      const endTime = parseISO(stats.timeEnd);
      const now = new Date();
      const minutesLeft = differenceInMinutes(endTime, now);
      setTimeLeft(formatTimeLeft(minutesLeft));
    } else {
      setTimeLeft(null);
    }
  }, [stats]);

  const formatTimeLeft = (minutes: number): string => {
    const days = Math.floor(minutes / (24 * 60));
    const hours = Math.floor((minutes % (24 * 60)) / 60);
    const remainingMinutes = minutes % 60;
    return `${days}d-${hours}h-${remainingMinutes}m`;
  };

  return (
    <>
      <div className="flex gap-4">
        <OrderCard title="ID SCANNER">
          <div className="flex justify-around gap-10">
            <Select onValueChange={(value: string) => setType(value)}>
              <SelectTrigger id="category">
                <SelectValue placeholder="Ticket" />
              </SelectTrigger>
              <SelectContent position="popper">
                <SelectItem value="Ticket">Ticket</SelectItem>
                <SelectItem value="Pass">Pass</SelectItem>
              </SelectContent>
            </Select>

            {type === "Ticket" ? (
              <Input
                className="mb-4"
                placeholder="Ticket ID"
                name="ticketId"
                value={formData.ticketId || ""}
                onChange={handleInputChange}
              ></Input>
            ) : (
              <Input
                className="mb-4"
                placeholder="Pass ID"
                name="passId"
                value={formData.passId || ""}
                onChange={handleInputChange}
              ></Input>
            )}
          </div>

          <label htmlFor="ID">Lift ID: </label>
          <Input
            className="mb-4"
            placeholder="LIFT ID"
            name="liftId"
            value={formData.liftId}
            onChange={handleInputChange}
          ></Input>

          <div className="flex justify-around">
            <Button onClick={handleSimulationSubmit}>Simulate</Button>
          </div>
        </OrderCard>

        {stats && (
          <OrderCard title="SIMULATION RESULTS">
            <h1>ACCESS: {stats.active ? "SUCCESSFULLY PASSED" : "ACCESS DENIED"}</h1>
            <p>Active: {stats.active ? "YES" : "NO"}</p>
            {type === "Ticket" ? <p>Amount of Rides Left: {stats.amountOfRidesLeft}</p>
            :
            <p>Amount of Rides: {stats.descentsNumber}</p>
            }
            <p>Total distance: {stats.totalDistance} m</p>
            <p>Time left: {timeLeft}</p>
          </OrderCard>
        )}
      </div>
    </>
  );
}
