package junsung.cinema.AuthPackage.entity;

public enum Grade implements discount {
    BRONZE {
        @Override
        public int discount(int price) {
            return price;
        }
    },
    GOLD{

        @Override
        public int discount(int price) {
            return price = price * 95/100;
        }
    },
    PLATINUM{

        @Override
        public int discount(int price) {
           return price = price * 90/100;
        }
    },
    DIAMOND{

        @Override
        public int discount(int price) {
          return  price = price * 85/100;
        }
    }
}
