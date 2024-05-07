import { Harmonogram } from "@/components/Harmonogram";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Button } from "./ui/button";
import axios from 'axios'
import {useState, useEffect} from 'react';


export function AdminHarmonogramComp() {

  const [newHarmonogram, setNewHarmonogram] = useState({
    name: '',
    distance: '',
    active: ''
  });
  const [items, setItems] = useState([])

  
  const handleSubmit = (event : any) => {
    event.preventDefault();
  
    axios.post('http://localhost:8080/lifts/addLift', newHarmonogram)
        .then(response => {
            setNewHarmonogram({
              name: '',
              distance: '',
              active: ''
            });
            fetchItems();  
            window.location.reload();

        })
        .catch(error => console.error('Error:', error));
  };

  
const fetchItems = () => {
  axios.get('http://localhost:8080/lifts')
      .then(response => setItems(response.data))
      .catch(error => console.error('Error:', error));
};

useEffect(() => {
  fetchItems();
}, []);

  
const handleInputChange = (e : React.ChangeEvent<HTMLInputElement>) => {
  const { name, value } = e.target;

  setNewHarmonogram(
    (Harmonogram) => ({
      ...Harmonogram,
      [name]: value,
  })
  )
};

  return (
    <>
      <p>Edit Harmonogram</p>
      <Harmonogram admin={true}/>
        <Card

          className="mt-4">
        <div className="flex gap-4 p-10">
          <Input placeholder="Name" name="name" value={newHarmonogram.name} onChange={handleInputChange}></Input>
          <Input placeholder="Distance" name="distance" value={newHarmonogram.distance} onChange={handleInputChange}></Input>
          <Input placeholder="Active" name="active" value={newHarmonogram.active} onChange={handleInputChange}></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
    </>  
  );
}
