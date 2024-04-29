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


type Props = {
  admin?: boolean | undefined;
}


export function Harmonogram({admin} : Props) {

  const [items, setItems] = useState([])

  const fetchItems = () => {
    axios.get('http://localhost:8080/Harmonogram')
        .then(response => setItems(response.data))
        .catch(error => console.error('Error:', error));
  };
  
  useEffect(() => {
    fetchItems();
  }, []);

  
const handleDelete = (id : any) => {
  axios.delete(`http://localhost:8080/deleteHarmonogram/${id}`)
      .then(response => {
          fetchItems(); 
      })
      .catch(error => console.error('Error:', error));
};

  return (
  
    <Table className="mt-12 w-1/2 mx-auto">
      <TableHeader>
        <TableRow>
          <TableHead className="w-[100px]">ID</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Distance</TableHead>
          <TableHead>Open</TableHead>
          <TableHead>Close</TableHead>
          <TableHead>Active</TableHead>
          {admin? <TableHead >Delete</TableHead> : undefined}

        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow>
          <TableCell className="font-medium">1</TableCell>
          <TableCell>Biała</TableCell>
          <TableCell>1921m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell>YES</TableCell>
          {admin? <TableCell ><Button className="w-full" onClick={handleDelete}>x</Button></TableCell> : undefined}
        </TableRow>
        <TableRow>
          <TableCell className="font-medium">2</TableCell>
          <TableCell>Różowa</TableCell>
          <TableCell>921m.</TableCell>
          <TableCell>7:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell>YES</TableCell>
          {admin? <TableCell ><Button className="w-full" onClick={handleDelete}>x</Button></TableCell> : undefined}

        </TableRow>
        <TableRow>
          <TableCell className="font-medium">3</TableCell>
          <TableCell>Tęczowa</TableCell>
          <TableCell>2145m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell>NO</TableCell>
          {admin? <TableCell ><Button className="w-full" onClick={handleDelete}>x</Button></TableCell> : undefined}

        </TableRow>
      </TableBody>
    </Table>
  );
}
