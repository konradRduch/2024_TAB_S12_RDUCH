import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "./ui/button";
import axios from "axios";
import {useState, useEffect} from 'react';


interface Items {
  id : number;
  name: string;
  distance: number;
  active: boolean;
}

type Props = {
  admin?: boolean | undefined;
}

export function Harmonogram({admin} : Props) {

  const [items, setItems] = useState<Items[]>([])

  const fetchItems = () => {
    axios.get('http://localhost:8080/lifts')
        .then(response => setItems(response.data))
        .catch(error => console.error('Error:', error));
  };
  
  useEffect(() => {
    fetchItems();
  }, []);

  
const handleDelete = (id : any) => {
  axios.delete(`http://localhost:8080/lifts/deleteLift/${id}`)
      .then(response => {
          fetchItems(); 
      })
      .catch(error => console.error('Error:', error));
};

  return (
  



    <Table className="mt-12 w-1/2 mx-auto">
          
      <TableHeader>
        <TableRow>
          <TableHead>ID</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Distance</TableHead>
          <TableHead>Active</TableHead>
          {admin? <TableHead >Delete</TableHead> : undefined}
        </TableRow>
      </TableHeader>
      <TableBody>
      {Array.isArray(items) && items.map((item, index) => (
      <TableRow key={index}>
          <TableCell className="font-medium">{index+1}</TableCell>
          <TableCell>{item.name}</TableCell>
          <TableCell>{item.distance}</TableCell>
          <TableCell>{item.active? "YES" : "NO"}</TableCell>
          {admin? <TableCell ><Button className="w-full" onClick={() => handleDelete(item.id)}>x</Button></TableCell> : undefined}
      </TableRow>
  ))} 
      </TableBody>
    </Table>
  );
}
