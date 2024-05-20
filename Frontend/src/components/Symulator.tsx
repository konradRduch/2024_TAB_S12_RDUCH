import { useState } from "react";
import { OrderCard } from "./OrderCard";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import axios from "axios";

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
}

export function SymulatorComp() {
  const [formData, setFormData] = useState<SymulatorTicket | SymulatorPass>({
    liftId: "",
  });

  const [stats, setStats] = useState<SimulationStats | null>(null);

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
        console.error("No ticket or pass ID provided.");
        return;
      }

      await axios.post(`http://localhost:8080/${endpoint}/bounce${endpoint}`, formData);

      const response = await axios.get(`http://localhost:8080/${endpoint}/summary`, {
        params: formData,
      });

      setStats(response.data);
      setFormData({
        liftId: "",
      });
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <>
      <div className="flex gap-4">
        <OrderCard title="ID SCANNER">
          <div className="flex justify-around gap-10">
            <Input
              className="mb-4"
              placeholder="Ticket ID"
              name="ticketId"
              value={formData.ticketId || ""}
              onChange={handleInputChange}
            ></Input>

            <Input
              className="mb-4"
              placeholder="Pass ID"
              name="passId"
              value={formData.passId || ""}
              onChange={handleInputChange}
            ></Input>
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
            <p>Active: {stats.active ? "YES" : "NO"}</p>
            <p>Total distance: {stats.totalDistance} m</p>
          </OrderCard>
        )}
      </div>
    </>
  );
}
