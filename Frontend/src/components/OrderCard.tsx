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



type Props = {
  children?: string | JSX.Element | JSX.Element[];
  content?: string | JSX.Element | JSX.Element[];
  title?: string;
  desc?: string;
}

export function OrderCard({children, content, title, desc} : Props) {
  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>{title}</CardTitle>
        <CardDescription>{desc}</CardDescription>
      </CardHeader>
      <CardContent>
      {children}
      </CardContent>

    </Card>
  )
}
