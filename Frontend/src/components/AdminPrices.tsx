import axios from "axios";
import { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "./ui/button";
import { Card } from "./ui/card";
import { Input } from "./ui/input";


interface Items {
  id : number;
  timeStart : string;
  timeEnd: string;
  ticketPrice: number;
  passPrice: boolean;
}

export function AdminPricesComp() {
  const [newHarmonogram, setNewHarmonogram] = useState({
    timeStart: '',
    timeEnd: '',
    ticketPrice: '',
    passPrice: '',
  });
  const [items, setItems] = useState<Items[]>([])

  const handleSubmit = (event: any) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/priceLists/addPriceList", newHarmonogram)
      .then((response) => {
        setNewHarmonogram({
          timeStart: '',
          timeEnd: '',
          ticketPrice: '',
          passPrice: '',
        });
        fetchItems();
        window.location.reload();
      })
      .catch((error) => console.error("Error:", error));
  };

  const fetchItems = () => {
    axios
      .get("http://localhost:8080/priceLists")
      .then((response) => setItems(response.data))
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    fetchItems();
  }, []);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setNewHarmonogram((Harmonogram) => ({
      ...Harmonogram,
      [name]: value,
    }));
  };

  const handleDelete = (id : any) => {
    axios.delete(`http://localhost:8080/priceLists/${id}`)
        .then(response => {
            fetchItems(); 
        })
        .catch(error => console.error('Error:', error));
  };


  return (
    <>
      <p>Prices</p>
      <Table className="mt-12 w-1/2 mx-auto">
          
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Pass Price</TableHead>
              <TableHead>Ticket Price</TableHead>
              <TableHead>Time Start</TableHead>
              <TableHead>TimeEnd</TableHead>
              <TableHead >Delete</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
          {Array.isArray(items) && items.map((item, index) => (
          <TableRow key={index}>
              <TableCell className="font-medium">{index+1}</TableCell>
              <TableCell>{item.passPrice}</TableCell>
              <TableCell>{item.ticketPrice}</TableCell>
              <TableCell>{item.timeStart}</TableCell>
              <TableCell>{item.timeEnd}</TableCell>
              <TableCell ><Button className="w-full" onClick={() => handleDelete(item.id)}>x</Button></TableCell>
          </TableRow>
      ))} 
          </TableBody>
        </Table>
        <Card

          className="mt-4">
        <div className="flex gap-4 p-10">
          <Input placeholder="Pass price" name="passPrice" value={newHarmonogram.passPrice} onChange={handleInputChange}></Input>
          <Input placeholder="Ticket price" name="ticketPrice" value={newHarmonogram.ticketPrice} onChange={handleInputChange}></Input>
          <Input placeholder="Time start" name="timeStart" value={newHarmonogram.timeStart} onChange={handleInputChange}></Input>
          <Input placeholder="Time end" name="timeEnd" value={newHarmonogram.timeEnd} onChange={handleInputChange}></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
    </>
  );
}
