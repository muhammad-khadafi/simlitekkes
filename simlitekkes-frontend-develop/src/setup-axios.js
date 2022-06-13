import axios from 'axios'

const url = (process.env.VUE_APP_BACKEND_URL) ? process.env.VUE_APP_BACKEND_URL
  : 'http://localhost:8001'

export const apiServer = axios.create({
  baseURL: url
})

export const axiosGlobalConfig = axios

export const fileUrl = url
