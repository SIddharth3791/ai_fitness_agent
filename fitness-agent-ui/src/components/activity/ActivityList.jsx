
import React, {use, useState, useEffect} from "react";
import { useNavigate } from "react-router";
import {Grid, Card, CardContent, Typography } from '@mui/material';
import { getActivities } from "../../service/api";

const ActivityList = () => {

    const [activities, setActivities] = useState([]);
    const navigate = useNavigate(); 

    const fetchActivities = async () => {
        try {
            const response = await getActivities();
            setActivities(response.data);
        } catch (error) {
            console.error("Error fetching activities:", error);
        }
    };  

    useEffect(() => {
        fetchActivities();
    }, []); 
    
    return (
        <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
            {activities.map((activity) => (
                <Grid item xs={12} sm={6} md={4} key={activity.id}>
                    <Card onClick={() => navigate(`/activities/${activity.id}`)} sx={{ cursor: 'pointer' }}>
                        <CardContent>
                            <Typography variant="h6">{activity.type}</Typography>
                            <Typography>Duration: {activity.duration}</Typography>
                            <Typography>Calories Burned: {activity.caloriesBurned}</Typography>
                        </CardContent>
                    </Card>
                </Grid>
            ))}
        </Grid>
    );
}

export default ActivityList;