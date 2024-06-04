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

/**
 * PassInfo interface represents the structure of the pass information.
 * @typedef {Object} PassInfo
 * @property {string} email - The email of the pass holder.
 * @property {number | string} phone - The phone number of the pass holder.
 * @property {number | string} id - The ID of the pass.
 */

interface PassInfo {
  email: string;
  phone: number | string;
  id: number | string;
}

/**
 * ResumeComp is a React functional component for resuming a pass.
 * @component
 * @returns {JSX.Element} - The JSX code for ResumeComp component.
 */

export function ResumeComp() {
  const [Freeze, setFreeze] = React.useState<PassInfo>({
    email: "",
    phone: "",
    id: "",
  });

  /**
   * Handles input changes and updates the state.
   * @param {React.ChangeEvent<HTMLInputElement>} e - The input change event.
   */
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setFreeze((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  /**
   * Handles form submission to resume a pass.
   * @param {React.FormEvent<HTMLFormElement>} event - The form submission event.
   */
  const handleSubmit = (event: any) => {
    event.preventDefault();

    const passData = {
      phone: Freeze.phone,
      email: Freeze.email,
    };

    axios
      .post(`http://localhost:8080/passes/resume/${Freeze.id}`, passData)
      .then((response) => {
        setFreeze({
          email: "",
          phone: "",
          id: "",
        });
        alert("Successfully resumed pass");
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Failed to resume the pass. Please try again.");
      });
  };

  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>BOUGHT PASS AND CANNOT USE IT RIGHT NOW?</CardTitle>
        <CardDescription>
          Freeze your pass for any time you need.
        </CardDescription>
      </CardHeader>
      <CardContent className="flex flex-col gap-[25px]">
        <div className="flex items-center gap-2">
          <label htmlFor="email" className="w-1/6">
            Email:
          </label>
          <Input
            name="email"
            value={Freeze.email}
            onChange={handleInputChange}
            placeholder="email"
          ></Input>
        </div>
        <div className="flex items-center gap-2">
          <label htmlFor="phone" className="w-1/6">
            Phone:
          </label>
          <Input
            name="phone"
            type="number"
            value={Freeze.phone}
            onChange={handleInputChange}
            placeholder="phone"
          ></Input>
        </div>
        <div className="flex items-center gap-2">
          <label htmlFor="id" className="w-1/6">
            Pass Id:
          </label>
          <Input
            name="id"
            type="number"
            value={Freeze.id}
            onChange={handleInputChange}
            placeholder="Pass id"
          ></Input>
        </div>
      </CardContent>
      <CardFooter className="flex justify-center">
        <Button className="w-full font-semibold" onClick={handleSubmit}>
          Send
        </Button>
      </CardFooter>
    </Card>
  );
}
