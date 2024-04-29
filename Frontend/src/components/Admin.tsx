import {OrderCard} from "@/components/OrderCard";
import { Button } from "./ui/button";
  

  export function AdminComp() {
    return (
        <div className="flex gap-4 flex-col lg:flex-row">
          <OrderCard title="Harmonogram">
            <p className="mb-4">See harmonogram, you can delete and add new ones here</p>
            <a href="/admin/adminHarmonogram"><Button>Harmonogram</Button></a>
          </OrderCard>
          <OrderCard title="Prices">
          <p className="mb-4">Change prices of the tickets and passes here</p>
            <a href="/admin/adminPrices"><Button>Prices</Button></a>
          </OrderCard>
          <OrderCard title="Report">
          <p className="mb-4">Create Raport, you can see all orders here</p>
            <a href="/admin/adminReport"><Button>Reports</Button></a>
          </OrderCard>
        </div>
    );
  }
  