import axios from "axios";

export default function getDbData(identifier: string) {

    switch (identifier) {
        case "airports":
            return axios.get("/api/data/airports")
                .then(response => {
                    return response.data
                })

        case "airlines":
            return axios.get("/api/data/airlines")
                .then(response => {
                    return response.data
                })
    }
}