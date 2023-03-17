package Module2;

public class ComplexNumbers {
    private double re = 0.0;
    private double im = 0.0;

    public ComplexNumbers(double re) {
        this.re = re;
    }

    public ComplexNumbers(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public ComplexNumbers add(ComplexNumbers z) {
        return new ComplexNumbers(this.re + z.re, this.im + z.im);
    }

    public ComplexNumbers sub(ComplexNumbers z) {
        return new ComplexNumbers(this.re - z.re, this.im - z.im);
    }

    public ComplexNumbers mul(ComplexNumbers z) {
        double newIm = this.re * z.im + this.im * z.re;
        double newRe = this.re * z.re - this.im * z.im;
        return new ComplexNumbers(newRe, newIm);
    }

    public ComplexNumbers div(ComplexNumbers z) throws ArithmeticException {
        double m = z.re * z.re + z.im * z.im;
        double t = this.re * z.re + this.im * z.im;
        double k = this.im * z.re - this.re * z.im;
        if (m == 0) {
            throw new ArithmeticException("Деление на ноль");
        } else {
            double newRe = t / m;
            double newIm = k / m;
            return new ComplexNumbers(newRe, newIm);
        }
    }

    public double mod() {
        return Math.sqrt(this.re * this.re + this.im * this.im);
    }

    @Override
    public String toString() {
        if (this.im < 0) {
            return String.format("%.3f", this.re) + " - " + String.format("%.3f", Math.abs(this.im)) + " * i";
        } else {
            return String.format("%.3f", this.re) + " + " + String.format("%.3f", this.im) + " * i";
        }
    }
}
