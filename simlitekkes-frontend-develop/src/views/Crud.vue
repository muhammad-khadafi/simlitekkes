<template>
  <v-container fill-height fluid>
    <v-row justify="center">
      <v-col cols="12">
        <v-card>
          <v-card-title>
            Data Pengajuan
            <div class="flex-grow-1"></div>
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :search="search"
            :headers="headers"
            :items="employee"
            :items-per-page="5"
            class="elevation-1"
          ></v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      search: "",
      employee: [],
      headers: [
        {
          text: "Name",
          align: "left",
          sortable: false,
          value: "name"
        },
        { text: "Address", value: "address" },
        { text: "Age", value: "age" },
        { text: "Role", value: "role" },
        { text: "Date of Birth", value: "dateofBirth" }
      ]
    };
  },
  created() {
    axios
      .get("http://localhost:8001/test") // Does a get request
      .then(response => {
        this.employee = response.data;
        console.log(response.data); // For now, logs out the response
      })
      .catch(error => {
        console.log("There was an error:", error.response); // Logs out the error
      });
  }
};
</script>
