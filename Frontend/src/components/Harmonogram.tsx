import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Button } from "./ui/button";

type Props = {
  admin?: boolean | undefined;
}

export function Harmonogram({admin} : Props) {
  return (
    <Table className="mt-12 w-1/2 mx-auto">
      <TableHeader>
        <TableRow>
          <TableHead className="w-[100px]">ID</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Distance</TableHead>
          <TableHead>Open</TableHead>
          <TableHead>Close</TableHead>
          <TableHead className="text-right">Active</TableHead>
          {admin? <TableHead className="text-right">Delete</TableHead> : undefined}

        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow>
          <TableCell className="font-medium">1</TableCell>
          <TableCell>Biała</TableCell>
          <TableCell>1921m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell className="text-right">YES</TableCell>
          {admin? <Button className="mx-2 my-4 w-full">x</Button> : undefined}
        </TableRow>
        <TableRow>
          <TableCell className="font-medium">2</TableCell>
          <TableCell>Różowa</TableCell>
          <TableCell>921m.</TableCell>
          <TableCell>7:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell className="text-right">YES</TableCell>
          {admin? <Button className="mx-2 my-4 w-full">x</Button> : undefined}

        </TableRow>
        <TableRow>
          <TableCell className="font-medium">3</TableCell>
          <TableCell>Tęczowa</TableCell>
          <TableCell>2145m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell className="text-right">NO</TableCell>
          {admin? <Button className="mx-2 my-4 w-full">x</Button> : undefined}

        </TableRow>
      </TableBody>
    </Table>
  );
}
