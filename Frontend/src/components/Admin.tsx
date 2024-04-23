import {OrderCard} from "@/components/OrderCard";
  

  export function AdminComp() {
    return (
        <div className="flex gap-4 flex-col lg:flex-row">
          <OrderCard title="Harmonogram"></OrderCard>
          <OrderCard title="Prices"></OrderCard>
          <OrderCard title="Raport"></OrderCard>
        </div>
    );
  }
  