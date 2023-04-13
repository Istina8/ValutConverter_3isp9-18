import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class CurrencyConverter extends JFrame implements ActionListener {
    // объявление компонентов окна
    private JTextField amountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JLabel resultLabel;

    public CurrencyConverter() {
        // настройка окна
        setTitle("Конвертер валют");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // создание компонентов
        JLabel amountLabel = new JLabel("Сумма:");
        amountField = new JTextField(10);
        JLabel fromCurrencyLabel = new JLabel("Из валюты:");
        fromCurrencyBox = new JComboBox<String>(new String[] {"USD", "EUR", "GBP", "JPY"});
        JLabel toCurrencyLabel = new JLabel("В валюту:");
        toCurrencyBox = new JComboBox<String>(new String[] {"USD", "EUR", "GBP", "JPY"});
        JButton convertButton = new JButton("Конвертировать");
        convertButton.addActionListener(this);
        resultLabel = new JLabel("");

        // размещение компонентов на панели
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromCurrencyLabel);
        panel.add(fromCurrencyBox);
        panel.add(toCurrencyLabel);
        panel.add(toCurrencyBox);
        panel.add(new JLabel());
        panel.add(convertButton);

        // добавление панели на окно
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(resultLabel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        // обработка нажатия кнопки "Конвертировать"
        double amount = Double.parseDouble(amountField.getText());
        String fromCurrency = (String)fromCurrencyBox.getSelectedItem();
        String toCurrency = (String)toCurrencyBox.getSelectedItem();
        double result = convertCurrency(amount, fromCurrency, toCurrency);
        resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency));
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        String apiKey = "7b2b6c2f49aa1871292fe75ae8a91b66";
        String url = "https://currate.ru/api/?get=currency_list&key=7b2b6c2f49aa1871292fe75ae8a91b66" + fromCurrency + "&to=" + toCurrency + "&amount=" + amount + "&apiKey=" + apiKey;

        try {

            // разбор ответа от API и извлечение курса обмена
            double exchangeRate = currencyRates;

            // выполнение конвертации валюты
           double result = amount * exchangeRate;

           return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }

    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.setVisible(true);
    }

    public void setCurrency(Map<String, Double> currencyRates)
    {

    }
}