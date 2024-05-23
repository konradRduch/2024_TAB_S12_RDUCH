import * as React from "react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import axios from "axios";

interface PassInfo {
  email: string;
  phone: string;
  id: number | string;
}

export function CardWithForm() {

  const [Freeze, setFreeze] = React.useState<PassInfo>({

    email: '',
    phone: '',
    id: ''
    
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
  
      setFreeze(prevState => ({
        ...prevState,
        [name]: value,
      }));
    };

    const handleSubmit = (event: any) => {
      event.preventDefault();
  
      axios.put(`http://localhost:8080/tickets/${Freeze.id}`, Freeze)
        .then(response => {
          setFreeze({
            email: '',
            phone: '',
            id: ''
          });
        })
        .catch(error => {console.error('Error:', error);
        alert('Failed to freeze the pass. Please try again.');
        }
      );
    };

  return (
    <Card className="w-full">
    <CardHeader>
      <CardTitle>Bought pass and cannot use it right now?</CardTitle>
      <CardDescription>Freeze your pass for any time you need.</CardDescription>
    </CardHeader>
    <CardContent className="flex flex-col gap-7">
      <div className="flex items-center gap-2">
        <label htmlFor="email" className="w-1/6">Email:</label>
        <Input name="email" value={Freeze.email} onChange={handleInputChange} placeholder="email"></Input>
      </div>
      <div className="flex items-center gap-2">
        <label htmlFor="phone" className="w-1/6">Phone:</label>
        <Input name="phone" type="tel" value={Freeze.phone} onChange={handleInputChange} placeholder="phone"></Input>
      </div>
      <div className="flex items-center gap-2">
        <label htmlFor="id" className="w-1/6">Pass Id:</label>
        <Input name="id" type="number" value={Freeze.id} onChange={handleInputChange} placeholder="Pass id"></Input>
      </div>
    </CardContent>
    <CardFooter className="flex justify-center">
      <Button className="w-full font-semibold" onClick={handleSubmit}>Send</Button>
    </CardFooter>
  </Card>
  
  );
}
