import { useState } from 'react';
import { OrderCard } from "@/components/OrderCard";
import { Button } from "@/components/ui/button";
import { CardFooter } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox"

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"


const Values = {
    price: 1,
    amount: 1,
  };

export function TicketComp() {
    const [values, setValues] = useState(Values);
    const [type, setType] = useState("Ticket");


    const handleInputChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setValues({
          ...values,
          [name]: value,
        });
      };


      function Ticket(){
        return<>
        <p>ticket</p>
          <Input
              type="number"
              name="amount"
              placeholder="Enter amount"
              min={0}
              value={values.amount}
              onChange={handleInputChange}
             
            />
            <Input
              type="number"
              name="price"
              placeholder="Enter price"
              min={0}
              value={values.price} 
              onChange={handleInputChange}
            />
            
        </>
      }

      function Pass(){
        return <>
         <Select>
                <SelectTrigger id="PassType">
                  <SelectValue placeholder="Normal" />
                </SelectTrigger>
                <SelectContent position="popper">
                  <SelectItem value="Normal">Normal</SelectItem>
                  <SelectItem value="Senior">Senior</SelectItem>
                  <SelectItem value="Kid">Kid</SelectItem>
                </SelectContent>
              </Select>
        <Input
              type="number"
              name="amount"
              placeholder="Enter amount"
              min={0}
              value={values.amount}
              onChange={handleInputChange}
             
            />
        </>
      }
        

    return (
     
        <div className="flex gap-4">
          
          <OrderCard title='BUY TICKET' desc='check out our wide variety of tickets'>
           <div className='grid gap-4'>
           <Select onValueChange={(value) => setType(value)}>
                <SelectTrigger id="category">
                  <SelectValue placeholder="Ticket" />
                </SelectTrigger>
                <SelectContent position="popper">
                  <SelectItem value="Ticket">Ticket</SelectItem>
                  <SelectItem value="Pass">Pass</SelectItem>
                </SelectContent>
              </Select>
            {type === "Ticket" ?  
            
            <Ticket/> : <Pass/>
            
            } 
              
              
          
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
          <OrderCard title='TOTAL' desc='Amount in pln'>
            <div className="text-center font-bold">{(values.amount*values.price).toFixed(2)} PLN</div> 
            <CardFooter className="flex justify-center">
              <Button className="w-full">BUY</Button>
            </CardFooter>
          </OrderCard>
        </div>
      );
    }


