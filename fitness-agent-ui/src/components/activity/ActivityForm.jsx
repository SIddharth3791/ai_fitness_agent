import React from "react";
import { addActivity } from "../../service/api";
import Box from '@mui/material/Box';
import { Button, FormControl, InputLabel, Select, TextField } from "@mui/material";
import MenuItem from '@mui/material/MenuItem';
import { Card, CardContent, CardActions, CardHeader, Typography } from '@mui/material';


const ActivityForm = ({ onActivityAdded }) => {
    const [activity, setActivity] = React.useState({userId: '', type: 'RUNNING', duration: '', caloriesBurned: '', additionalMetrics: {} });

    const handleSubmit = async(event) => {
        event.preventDefault();
        // Handle form submission logic here
        try{
            // Example: Send form data to the server
            console.log("Form submitted");
            await addActivity(activity)
            onActivityAdded();
            setActivity({userId: '', type: 'RUNNING', duration: '', caloriesBurned: '' });
        }catch(error){
            console.error("Error submitting form:", error);
        }
    };

    return (
        <Card component="form" onSubmit={handleSubmit} sx={{ mb: 1 }}>
            <CardContent  color="Red"> 
                <Typography gutterBottom sx={{ fontSize: 20, fontWeight: 'bold', textAlign: 'center' }}>
                🏋️ Activity Tracker
                </Typography>
            </CardContent>
            <CardContent>
                    <FormControl fullWidth sx={{ mb: 2 }}>
                    <InputLabel id="activity-type-label">Activity Type</InputLabel>
                    <Select 
                            id="activity-type-label"
                        label="Activity Type"
                            value={activity.type}
                            onChange={(e) => setActivity({ ...activity, type: e.target.value })}>
                            <MenuItem value="RUNNING">Running</MenuItem>
                            <MenuItem value="CYCLING">Cycling</MenuItem>
                            <MenuItem value="SWIMMING">Swimming</MenuItem>
                    </Select>
                    </FormControl>
                    <TextField
                        fullWidth
                        label="Duration (minutes)"
                        type="number"
                        value={activity.duration}
                        onChange={(e) => setActivity({ ...activity, duration: e.target.value })}
                        sx={{ mb: 2 }} 
                    />
                    
                    <TextField
                        fullWidth
                        label="Calories Burned"
                        type="number"
                        value={activity.caloriesBurned}
                        onChange={(e) => setActivity({ ...activity, caloriesBurned: e.target.value })}
                        sx={{ mb: 2 }} 
                    />
                    <CardActions>
                        <Button type="submit" variant="contained" color="primary">
                            Add Activity
                        </Button>
                    </CardActions>
        </CardContent>
     </Card>
    );
}

export default ActivityForm;