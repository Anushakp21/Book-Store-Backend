        package com.example.Book.Store.Application.responsedto;


        import com.example.Book.Store.Application.entity.Image;

        public class BookResponse {

            private Integer bookId;
            private String bookName;
            private String authorName;
            private String description;
            private Double price;
            private Long quantity;
            private String bookImage;

            public Integer getBookId() {
                return bookId;
            }

            public void setBookId(Integer bookId) {
                this.bookId = bookId;
            }

            public String getBookName() {
                return bookName;
            }

            public void setBookName(String bookName) {
                this.bookName = bookName;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public Long getQuantity() {
                return quantity;
            }

            public void setQuantity(Long quantity) {
                this.quantity = quantity;
            }

            public String getBookImage() {
                return bookImage;
            }

            public void setBookImage(String bookImage) {
                this.bookImage = bookImage;
            }
        }
