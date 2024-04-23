import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

export function Harmonogram() {
  return (
    <Table className="mt-12 w-1/2 mx-auto">
      <TableHeader>
        <TableRow>
          <TableHead className="w-[100px]">ID</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Distance</TableHead>
          <TableHead>Open</TableHead>
          <TableHead>Close</TableHead>
          <TableHead className="text-right">active</TableHead>
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
        </TableRow>
        <TableRow>
          <TableCell className="font-medium">1</TableCell>
          <TableCell>Biała</TableCell>
          <TableCell>1921m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell className="text-right">YES</TableCell>
        </TableRow>
        <TableRow>
          <TableCell className="font-medium">1</TableCell>
          <TableCell>Biała</TableCell>
          <TableCell>1921m.</TableCell>
          <TableCell>8:00am</TableCell>
          <TableCell>9:00pm</TableCell>
          <TableCell className="text-right">YES</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
}
