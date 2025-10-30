import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Утилитный класс для выполнения математических операций.
 * Класс объявлен как final с приватным конструктором для предотвращения инстанцирования.
 */
public final class MathUtils {
    
    /**
     * Приватный конструктор для предотвращения создания экземпляров класса.
     */
    private MathUtils() {
        throw new AssertionError("Нельзя создать экземпляр утилитного класса");
    }
    
    // 1. Метод max
    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    
    // 2. Метод min
    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    
    // 3. Метод solveLinear
    public static Optional<Double> solveLinear(double a, double b) {
        if (a == 0) {
            if (b == 0) {
                // Бесконечное количество решений
                return Optional.empty();
            } else {
                // Нет решений
                return Optional.empty();
            }
        }
        return Optional.of(-b / a);
    }
    
    // 4. Метод getSign
    public static String getSign(int number) {
        if (number > 0) {
            return "Положительное";
        } else if (number < 0) {
            return "Отрицательное";
        } else {
            return "Нулевое";
        }
    }
    
    // 5. Метод solveQuadratic
    public static List<Double> solveQuadratic(double a, double b, double c) {
        List<Double> solutions = new ArrayList<>();
        
        if (a == 0) {
            // Линейное уравнение
            Optional<Double> solution = solveLinear(b, c);
            solution.ifPresent(solutions::add);
            return solutions;
        }
        
        double discriminant = b * b - 4 * a * c;
        
        if (discriminant > 0) {
            // Два действительных корня
            double sqrtD = Math.sqrt(discriminant);
            solutions.add((-b + sqrtD) / (2 * a));
            solutions.add((-b - sqrtD) / (2 * a));
        } else if (discriminant == 0) {
            // Один действительный корень
            solutions.add(-b / (2 * a));
        }
        // Если discriminant < 0 - нет действительных корней, возвращаем пустой список
        
        return solutions;
    }
    
    // 6. Метод sumEvenNumbers
    public static int sumEvenNumbers(int n) {
        if (n < 2) {
            return 0;
        }
        
        int sum = 0;
        for (int i = 2; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }
    
    // 7. Метод factorial
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал определен только для неотрицательных чисел");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    // 8. Метод isPrime
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // 9. Метод arithmeticMean
    public static double arithmeticMean(double[][] matrix) {
        validateMatrix(matrix);
        
        double sum = 0;
        int count = 0;
        
        for (double[] row : matrix) {
            for (double element : row) {
                sum += element;
                count++;
            }
        }
        
        return sum / count;
    }
    
    // 10. Метод geometricMean
    public static double geometricMean(double[][] matrix) {
        validateMatrix(matrix);
        
        double product = 1.0;
        int count = 0;
        
        for (double[] row : matrix) {
            for (double element : row) {
                if (element <= 0) {
                    throw new IllegalArgumentException("Среднее геометрическое определено только для положительных чисел");
                }
                product *= element;
                count++;
            }
        }
        
        return Math.pow(product, 1.0 / count);
    }
    
    // 11. Метод sumEvenColumns
    public static double sumEvenColumns(double[][] matrix) {
        validateMatrix(matrix);
        
        double sum = 0;
        
        for (double[] row : matrix) {
            for (int j = 0; j < row.length; j += 2) {
                sum += row[j];
            }
        }
        
        return sum;
    }
    
    // 12. Метод productOddRows
    public static double productOddRows(double[][] matrix) {
        validateMatrix(matrix);
        
        double product = 1.0;
        boolean hasOddRows = false;
        
        for (int i = 1; i < matrix.length; i += 2) {
            hasOddRows = true;
            for (double element : matrix[i]) {
                product *= element;
            }
        }
        
        if (!hasOddRows) {
            return 0; // Если нет строк с нечетными индексами
        }
        
        return product;
    }
    
    /**
     * Валидация матрицы
     * @param matrix матрица для проверки
     * @throws IllegalArgumentException если матрица null, пустая или содержит null-строки
     */
    private static void validateMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Матрица не может быть null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Матрица не может быть пустой");
        }
        for (double[] row : matrix) {
            if (row == null) {
                throw new IllegalArgumentException("Строки матрицы не могут быть null");
            }
            if (row.length == 0) {
                throw new IllegalArgumentException("Строки матрицы не могут быть пустыми");
            }
        }
    }
}