import { Harmonogram } from "@/components/Harmonogram";
import MainCard from "./Main-card.astro";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Button } from "./ui/button";

export function AdminHarmonogramComp() {
  return (
    <>
      <p>Edit Harmonogram</p>
      <Harmonogram admin={true}/>
        <Card

          className="mt-4">
        <div className="flex gap-4 p-10">
          <Input placeholder="Name"></Input>
          <Input placeholder="Distance"></Input>
          <Input placeholder="Open"></Input>
          <Input placeholder="Close"></Input>
          <Input placeholder="Active"></Input>
          <Button>ADD</Button>
        </div>
      </Card>
    </>  
  );
}
