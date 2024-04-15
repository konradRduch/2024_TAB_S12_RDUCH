import { useState } from 'react';
import { OrderCard } from "@/components/OrderCard";
import { Button } from "@/components/ui/button";
import { CardFooter } from "@/components/ui/card";
import { Input } from "@/components/ui/input";


const Values = {
    price: 1,
    amount: 1,
  };

export function TicketComp() {
    const [values, setValues] = useState(Values);
    const [total, setTotal] = useState(1);

    const handleInputChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setValues({
          ...values,
          [name]: value,
        });
        setTotal(values.price * values.amount)
      };

    return (
     
        <div className="flex gap-4">
          <OrderCard title='BUY TICKET' desc='check out our wide variety of tickets'>
            <Input
              type="number"
              name="amount"
              placeholder="Enter amount"
              value={values.amount}
              onChange={handleInputChange}
             
            />
            <Input
              type="number"
              name="price"
              placeholder="Enter price"
              value={values.price} 
              onChange={handleInputChange}
             
            />
          </OrderCard>
          <OrderCard title='TOTAL' desc='Amount in pln'>
            <div className="text-center font-bold">{total.toFixed(2)} PLN</div> 
            <CardFooter className="flex justify-center">
              <Button className="w-full">BUY</Button>
            </CardFooter>
          </OrderCard>
        </div>
      );
    }


