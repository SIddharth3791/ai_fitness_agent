import { Button, Box } from "@mui/material"
import { use, useContext } from "react"
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router"
import { setCredentials } from "./store/authSlice"
import { AuthContext } from "react-oauth2-code-pkce"  
import { useDispatch } from "react-redux"
import { useEffect, useState } from "react"
import ActivityList from "./components/activity/ActivityList.jsx"
import ActivityForm from "./components/activity/ActivityForm.jsx"
import ActivityDetail from "./components/activity/ActivityDetail.jsx"

const ActivityPage = () => {
  return (
    <Box component="section" sx={{ p: 2, border: '1px dashed grey' }}>
      <ActivityForm onActivityAdded={() => window.location.reload()} />
      <ActivityList />
    </Box>
  );
}

function App() {

  const {token, tokenData, logIn, logOut, isAuthenticated} = useContext(AuthContext)
  const dispatch = useDispatch()
  const [isAuth, setIsAuth] = useState(false)

  useEffect(() => {
    if (token) {
      dispatch(setCredentials({token, user: tokenData}))
      setIsAuth(true)
    }
  },[token, tokenData, dispatch])
  return (
    <Router>
      {!token ? ( 
        <Button 
          variant="contained" 
          color="info"
          onClick={() => logIn()}> Login</Button>
          ) : (
            <Box component="section" sx={{ p: 2, border: '1px dashed grey' }}>
              <Routes>
                <Route path="/activities" element={<ActivityPage />} />
                <Route path="/activities/:id" element={<ActivityDetail />} />
                <Route path="/" element={ token ? <Navigate to="/activities" /> : <div>Welcome Please Log In</div>} />
              </Routes>
            </Box>
          )

          }
    </Router>
  )
}

export default App
