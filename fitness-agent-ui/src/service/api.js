import axios from 'axios';

const API_URL = 'http://localhost:8085/api';

const userId = localStorage.getItem('userId');

const api = axios.create({
  baseURL: API_URL,
});

api.interceptors.request.use(
  (config) => {
   const userId = localStorage.getItem('userId');
   const token = localStorage.getItem('token');
   
   if (userId) {
     config.headers['X-User-ID'] = userId;
   }

    if (token) {
     config.headers['Authorization'] = `Bearer ${token}`;
   }

    return config;
  },
  (error) => {
    // Handle request errors
    return Promise.reject(error);
  }
);  

export const getActivities = () => {
  return api.get('/activities');
};

export const addActivity = (activity) => {
    activity.userId = userId
  return api.post('/activities', activity);
};

export const getActivityDetails = (activityId) => {
  return api.get(`/recommendation/activity/${activityId}`);
};
