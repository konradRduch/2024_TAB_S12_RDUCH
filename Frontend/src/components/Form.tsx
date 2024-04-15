import * as React from "react"

import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"

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
              <Label htmlFor="name">Name</Label>
              <Input id="name" placeholder="email" />
            </div>
            <div className="flex flex-col space-y-1.5">
              <Label>Category</Label>
              <Select>
                <SelectTrigger id="category">
                  <SelectValue placeholder="Select" />
                </SelectTrigger>
                <SelectContent position="popper">
                  <SelectItem value="pass">passes</SelectItem>
                  <SelectItem value="ticket">tickets</SelectItem>
                  <SelectItem value="cancelation">cancelation</SelectItem>
                  <SelectItem value="else">else</SelectItem>
                </SelectContent>
              </Select>
              <Textarea></Textarea>
            </div>
          </div>
        </form>
      </CardContent>
      <CardFooter className="flex justify-center">
        <Button className="w-[20vw]">Send</Button>
      </CardFooter>
    </Card>
  )
}
