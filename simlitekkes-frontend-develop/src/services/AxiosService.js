import axios from 'axios'
    
const apiServer = axios.create({  
  baseURL: `http://localhost:8001`,
  withCredentials: false, // This is the default
  headers: {           
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export default {
  getEmployee() {
    return apiServer.get('/test')
  }
}