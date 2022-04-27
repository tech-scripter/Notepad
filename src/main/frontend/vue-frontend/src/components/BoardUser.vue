<template>
    <div class="editor"><Editor/></div>
</template>
<script>
import UserService from "../services/user.service";
import Editor from "../components/Editor.vue";

export default {
  name: "User-page",

  components: {
      Editor,
  },

  data() {
    return {
      content: "",
    };
  },
  mounted() {
    UserService.getUserBoard().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
};
</script>

<style scoped>
.editor {
  width: 100% !important;
}
</style>