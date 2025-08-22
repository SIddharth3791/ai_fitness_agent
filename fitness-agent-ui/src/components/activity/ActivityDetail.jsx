import React, {useState, useEffect} from "react";
import { getActivityDetails } from "../../service/api";
// import { Box, Card, CardHeader, CardContent, Divider, Typography,Stack } from "@mui/material";
import { useParams } from "react-router";

import { Box, Card, CardContent, Typography, Divider, Chip } from '@mui/material';
import FitnessCenterIcon from '@mui/icons-material/FitnessCenter';
import SportsKabaddiIcon from '@mui/icons-material/SportsKabaddi';
import WhatshotIcon from '@mui/icons-material/Whatshot';
import TipsAndUpdatesIcon from '@mui/icons-material/TipsAndUpdates';
import HealthAndSafetyIcon from '@mui/icons-material/HealthAndSafety';

const ActivityDetail = () => {
    const { id } = useParams();
    const [activity, setActivity] = useState(null);
    const [recommendations, setRecommendations] = useState(null);

    useEffect(() => {
        fetchActivityDetails();
    }, [id]);

    const fetchActivityDetails = async () => {
        try {
            const response = await getActivityDetails(id);
            setActivity(response.data);
            setRecommendations(response.data.recommendation);
        } catch (error) {
            console.error("Error fetching activity details:", error);
        }
    };
    if (!activity) {
        return <Typography >Loading activity details...</Typography>;
    }   

    return (

        // <Box sx={{ maxWidth: 800, p: 2, mx: 'auto' }}>
        //     <Card sx={{ mb: 2}}>
        //         <CardContent>
        //             <Typography variant="h5" gutterBottom>Activity Details</Typography>
        //             <Typography > Type: {activity.type}</Typography>
        //             <Typography > Duration: {activity.duration}</Typography>
        //             <Typography > Calories Burned: {activity.caloriesBurned}</Typography>
        //             <Typography > Date: {new Date(activity.date).toLocaleDateString()}</Typography>
        //         </CardContent>
        //     </Card>
        //     {recommendations && (
        //         <Card>
        //             <CardContent>
        //                 <Typography variant="h5" gutterBottom>Activity Recommendations</Typography>
        //                 <Typography variant="h6">Analysis</Typography>
        //                 <Typography component="p">{activity.recommendation}</Typography>

        //                 <Divider sx={{ my: 2 }} />
        //                 <Typography variant="h6">Improvements</Typography>
        //                 {activity.improvements?.map((improvement, index) => (
        //                     <div key={index}>
        //                         <Typography component="p">{improvement.area}</Typography>
        //                         <Typography component="p">{improvement.recommendation}</Typography>
        //                     </div>
        //                 ))}
        //                 <Divider sx={{ my: 2 }} />
        //                 <Typography variant="h6">Suggestions</Typography>
        //                 {activity.suggestions?.map((suggestion, index) => (
        //                     <div key={index}>
        //                         <Typography component="p">{suggestion.workout}</Typography>
        //                         <Typography key={index} component="p">{suggestion.description}</Typography>
        //                     </div>
                            
        //                 ))}
        //                 <Divider sx={{ my: 2 }} />
        //                 <Typography variant="h6">Safety</Typography>
        //                 {activity.safety?.map((safety, index) => (
        //                     <Typography key={index} component="p">{safety}</Typography>
        //                 ))}
        //             </CardContent>
        //         </Card>
        //     )}
        // </Box>
    <Box sx={{ maxWidth: 900, p: 3, mx: 'auto' }}>
      {/* Activity Details Card */}
      <Card
        sx={{
          mb: 3,
          borderRadius: 3,
          boxShadow: 4,
          background: 'linear-gradient(135deg, #e3f2fd, #ffffff)',
        }}
      >
        <CardContent>
          <Typography variant="h5" gutterBottom sx={{ fontWeight: 700, color: '#1976d2' }}>
            <FitnessCenterIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
            Type: {activity.activityType}
          </Typography>
          <Typography> ⏱ Duration: {activity.duration} mins</Typography>
          <Typography> <WhatshotIcon sx={{ mr: 1, color: '#ef5350' }}/> Calories Burned: {activity.caloriesBurned}</Typography>
          <Typography> 📅 Date: {new Date(activity.date).toLocaleDateString()}</Typography>
        </CardContent>
      </Card>

      {/* Recommendations */}
      {recommendations && (
        <Card
          sx={{
            borderRadius: 3,
            boxShadow: 6,
            backgroundColor: '#fafafa',
          }}
        >
          <CardContent>
            <Typography variant="h5" gutterBottom sx={{ fontWeight: 700, color: '#2e7d32' }}>
              <TipsAndUpdatesIcon sx={{ mr: 1, verticalAlign: 'middle' }} />
              Activity Recommendations
            </Typography>

            {/* Analysis */}
            <Typography variant="h6" sx={{ color: '#388e3c', mt: 2 }}>
              Analysis
            </Typography>
            <Typography component="p" sx={{ mb: 2 }}>
              {activity.recommendation}
            </Typography>

            <Divider sx={{ my: 2 }} />

            {/* Improvements */}
            <Typography variant="h6" sx={{ color: '#f57c00' }}>
              Improvements
            </Typography>
            {activity.improvements?.map((improvement, index) => (
              <Box
                key={index}
                sx={{
                  backgroundColor: '#fff3e0',
                  p: 1.5,
                  borderRadius: 2,
                  mb: 1,
                }}
              >
                <Typography sx={{ fontWeight: 600 }}>{improvement.area}</Typography>
                <Typography>{improvement.recommendation}</Typography>
              </Box>
            ))}

            <Divider sx={{ my: 2 }} />

            {/* Suggestions */}
            <Typography variant="h6" sx={{ color: '#1565c0' }}>
              Suggestions
            </Typography>
            {activity.suggestions?.map((suggestion, index) => (
              <Box
                key={index}
                sx={{
                  backgroundColor: '#e3f2fd',
                  p: 1.5,
                  borderRadius: 2,
                  mb: 1,
                }}
              >
                <Typography sx={{ fontWeight: 600 }}>{suggestion.workout}</Typography>
                <Typography>{suggestion.description}</Typography>
              </Box>
            ))}

            <Divider sx={{ my: 2 }} />

            {/* Safety */}
            <Typography variant="h6" sx={{ color: '#d32f2f' }}>
              Safety
            </Typography>
            {activity.safety?.map((safety, index) => (
              <Chip
                key={index}
                icon={<HealthAndSafetyIcon />}
                label={safety}
                color="error"
                variant="outlined"
                sx={{ m: 0.5 }}
              />
            ))}
          </CardContent>
        </Card>
      )}
    </Box>
  );
}

export default ActivityDetail;