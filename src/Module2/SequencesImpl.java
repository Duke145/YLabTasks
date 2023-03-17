package Module2;

public class SequencesImpl implements Sequences {
    @Override
    public void a(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 2;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem += 2;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void b(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 1;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem += 2;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void c(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 1;
            int step = 3;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem += step;
                step += 2;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void d(int n) {
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                System.out.print((i == (n - 1)) ? (int) Math.pow(i + 1, 3) : (int) Math.pow(i + 1, 3) + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void e(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 1;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem *= -1;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void f(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 1;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem = (Math.abs(elem) + 1) * elem / Math.abs(elem) * (-1);
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void g(int n) {
        if (n > 0) {
            int counter = n;
            int elem = 1;
            int step = 3;
            while (counter > 0) {
                counter--;
                System.out.print((counter == 0) ? elem : elem + ", ");
                elem = (Math.abs(elem) + step) * elem / Math.abs(elem) * (-1);
                step += 2;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void h(int n) {
        if (n > 0) {
            int elem = 1;
            int module = 0;
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 0) {
                    module = elem;
                    elem = 0;
                } else {
                    elem = module + 1;
                    module++;
                }
                System.out.print((i == n) ? elem : elem + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void i(int n) {
        if (n > 0) {
            long elem = 1;
            int module = 1;
            for (int i = 1; i <= n; i++) {
                System.out.print((i == n) ? elem : elem + ", ");
                module++;
                elem *= module;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }

    @Override
    public void j(int n) {
        if (n > 0) {
            int elem = 1;
            int prev = 0;
            int next;
            for (int i = 1; i <= n; i++) {
                System.out.print((i == n) ? elem : elem + ", ");
                next = elem + prev;
                prev = elem;
                elem = next;
            }
            System.out.println();
        } else {
            System.out.println("Некорректно введены данные");
        }
    }
}
