var example = new Vue({
    el: '#example',
    data: {
      message: '',
    },
    computed: {   
      columnCount: function () {
        return this.message.length;
      },
      
      wordCount: function () {
        return this.message.match(/\s+/g)?.length || 0;
      },
      
      stringCount: function () {
        return this.message.length ? this.message.split(/\r\n|\r|\n/).length : 0;
      }
    }
  })