import { Harmonogram } from "@/components/Harmonogram";
import { Input } from "./ui/input";
import { Card } from "./ui/card";
import { Button } from "./ui/button";
import axios from 'axios'
import {useState, useEffect} from 'react';


export function AdminHarmonogramComp() {

  const [newHarmonogram, setNewHarmonogram] = useState({
    open: '',
    close: '',
    lift: {
      name: '',
      active: '',
      distance: ''
    }
  });
  const [items, setItems] = useState([])

  
  const handleSubmit = (event : any) => {
    event.preventDefault();
  
    axios.post('http://localhost:8080/skiSchedules/addSkiSchedule', newHarmonogram)
        .then(response => {
            setNewHarmonogram({
              open: '',
              close: '',
              lift: {
                name: '',
                active: '',
                distance: ''
              }
            });
            fetchItems();  
            window.location.reload();

        })
        .catch(error => console.error('Error:', error));
  };

  
const fetchItems = () => {
  axios.get('http://localhost:8080/skiSchedules/dto')
      .then(response => setItems(response.data))
      .catch(error => console.error('Error:', error));
};

useEffect(() => {
  fetchItems();
}, []);

  
const handleInputChange = (e : React.ChangeEvent<HTMLInputElement>) => {
  const { name, value } = e.target;

  setNewHarmonogram(prevState => ({

    ...prevState,
    [name]: value,
    lift: {
      ...prevState.lift,
      [name]: value
    }
  }));
};

  return (
    <>
      <p>Edit Harmonogram</p>
      <Harmonogram admin={true}/>
        <Card

          className="mt-4">
        <div className="flex gap-4 p-10">
          <Input placeholder="Name" name="name" value={newHarmonogram.lift.name} onChange={handleInputChange}></Input>
          <Input placeholder="Distance" name="distance" value={newHarmonogram.lift.distance} onChange={handleInputChange}></Input>
          <Input placeholder="Open" name="open" value={newHarmonogram.open} onChange={handleInputChange}></Input>
          <Input placeholder="Close" name="close" value={newHarmonogram.close} onChange={handleInputChange}></Input>
          <Input placeholder="Active" name="active" value={newHarmonogram.lift.active} onChange={handleInputChange}></Input>
          <Button onClick={handleSubmit}>ADD</Button>
        </div>
      </Card>
    </>  
  );
}


