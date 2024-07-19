package com.awaion.demo031.design_mode;

import lombok.Getter;

public class BuilderDemo {
    public static void main(String[] args) {
        // 建造者模式(Builder):将一个复杂对象的构建与他的表示相分离,使得同样的构建过程可以创建不同的表示.
        ProductE product = new ProductE.Builder()
                .setPartA("A部分")
                .setPartB("B部分")
                .setPartC("C部分")
                .build();

        System.out.println(product.getPartA());
        System.out.println(product.getPartB());
        System.out.println(product.getPartC());
    }
}

class ProductE {
    @Getter
    private String partA;
    @Getter
    private String partB;
    @Getter
    private String partC;

    private ProductE(Builder builder) {
        this.partA = builder.partA;
        this.partB = builder.partB;
        this.partC = builder.partC;
    }

    public static class Builder {
        private String partA;
        private String partB;
        private String partC;

        public Builder setPartA(String partA) {
            this.partA = partA;
            return this;
        }

        public Builder setPartB(String partB) {
            this.partB = partB;
            return this;
        }

        public Builder setPartC(String partC) {
            this.partC = partC;
            return this;
        }

        public ProductE build() {
            return new ProductE(this);
        }
    }
}
