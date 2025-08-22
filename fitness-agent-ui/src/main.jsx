
import App from './App.jsx'
import { Provider } from 'react-redux'
import { store } from './store/store.js'
import ReactDOM from 'react-dom/client'
import {authConfig} from './authConfig.js'
import { AuthProvider } from 'react-oauth2-code-pkce'

const root =  ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <AuthProvider authConfig={authConfig} loadingComponent={<div>Loading...</div>}>
      <Provider store={store}>
        <App />
      </Provider>
    </AuthProvider>
)
