import {OrderCard} from "@/components/OrderCard";
import { Button } from "./ui/button";
  

  export function AdminComp() {
    return (
        <div className="grid grid-cols-2 gap-4 lg:flex lg:flex-row">
          <OrderCard title="Lifts">
            <p className="mb-4">See a list of lifts, you can delete and add new ones here</p>
            <a href="/admin/adminHarmonogram"><Button>Lifts</Button></a>
          </OrderCard>
          <OrderCard title="Prices">
          <p className="mb-4">Change prices of the tickets and passes here</p>
            <a href="/admin/adminPrices"><Button>Prices</Button></a>
          </OrderCard>
          <OrderCard title="Report">
          <p className="mb-4">Create Raport, you can see all orders here</p>
            <a href="/admin/adminReport"><Button>Reports</Button></a>
          </OrderCard>
          <OrderCard title="Lifts">
          <p className="mb-4">menage lifts, you can see all lifts here and add new new ones</p>
            <a href="/admin/adminLifts"><Button>Lifts</Button></a>
          </OrderCard>
        </div>
    );
  }
  