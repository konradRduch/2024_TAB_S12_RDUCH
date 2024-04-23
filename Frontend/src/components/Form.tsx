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
import { Textarea } from "@/components/ui/textarea";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export function CardWithForm() {
  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>Have questions?</CardTitle>
        <CardDescription>Ask us.</CardDescription>
      </CardHeader>
      <CardContent>
        <form>
          <div className="grid w-full items-center gap-4">
            <div className="flex flex-col space-y-1.5">
              <Input id="name" placeholder="Your email" />
            </div>
            <div className="flex flex-col space-y-1.5">
              <Select>
                <SelectTrigger id="category">
                  <SelectValue placeholder="Select" />
                </SelectTrigger>
                <SelectContent position="popper">
                  <SelectItem value="pass">Pass</SelectItem>
                  <SelectItem value="ticket">Ticket</SelectItem>
                  <SelectItem value="cancelation">Cancel</SelectItem>
                  <SelectItem value="else">Other</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <Textarea placeholder="Describe problem..."></Textarea>
          </div>
        </form>
      </CardContent>
      <CardFooter className="flex justify-center">
        <Button className="w-full">Send</Button>
      </CardFooter>
    </Card>
  );
}
