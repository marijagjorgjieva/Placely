const SERVER_ADDR = process.env.REACT_APP_SERVER_ADDRESS;
export async function sendRequest (str)  {
    const result = await fetch(SERVER_ADDR + '/api/home/userPreferences?'+str, {mode:'cors'})
    return {
        "res" : await result.json()
    }

}