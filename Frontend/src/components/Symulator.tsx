import { OrderCard } from "./OrderCard";
import { Button } from "./ui/button";
import { Input } from "./ui/input";


export function SymulatorComp() {
  return (
    <>
      <div className="flex gap-4">
        <OrderCard title="ID SCANNER">
          <label htmlFor="ID">Ticket ID: </label>
          <Input className="mb-4" placeholder="Your ID"></Input>
          <label htmlFor="ID">Lift ID: </label>
          <Input className="mb-4" placeholder="LIFT ID"></Input>
          <Button>Simulate</Button>
        </OrderCard>

        <OrderCard title="PASS SIMULATOR">
          <p>Active: YES </p>
          <p>Total distance: 5193 m.</p>
          <p>Time until next ride is avaible: 1:23 </p>
        </OrderCard>
      </div>
    </>  
  );
}
