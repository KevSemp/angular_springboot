import {environment} from 'src/environments/environment';
const {API_HOST} = environment;

//--endpoints list
export const USERS = `${API_HOST}/user`;

export const GET_ALL_EXCHANGE_TYPE = `${API_HOST}/exchangerate/all`
export const GET_AVERAGE_BY_RANGE = `${API_HOST}/exchangerate`
export const GET_VARIABLES = `${API_HOST}/exchangerate/variables`
