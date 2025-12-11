package ui;


import javax.swing.*;
import org.testng.TestNG;
import java.util.*;

public class TestRunnerUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Runner");

        // Dropdown για χώρα
        String[] countries = {"Afghanistan","Albania","Algeria","Amer.Virgin Is.","Andorra","Angola","Anguilla","Antarctica",
        	    "Antigua/Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas",
        	    "Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Blue",
        	    "Bolivia","Bosnia-Herz.","Botswana","Bouvet Islands","Brazil","Brit.Ind.Oc.Ter","Brit.Virgin Is.",
        	    "Brunei Daruss.","Bulgaria","Burkina Faso","Burma","Burundi","Cambodia","Cameroon","Canada",
        	    "Cape Verde","CAR","Cayman Islands","Chad","Chile","China","Christmas Island","Coconut Islands",
        	    "Colombia","Comoros","Cook Islands","Costa Rica","Cote d","Croatia","Cuba","Curacao","Cyprus",
        	    "Czech Republic","Dem. Rep. Congo","Denmark","Djibouti","Dominica","Dominican Rep.","Dutch Antilles",
        	    "East Timor","Ecuador","Egypt","El Salvador","Equatorial Guin","Eritrea","Estonia","Ethiopia",
        	    "Falkland Islnds","Faroe Islands","Fiji","Finland","France","Frenc.Polynesia","French Guayana",
        	    "French S.Territ","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland",
        	    "Grenada","Guadeloupe","Guam","Guatemala","Guinea","Guinea-Bissau","Guyana","Haiti","Heard/McDon.Isl",
        	    "Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Israel",
        	    "Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait",
        	    "Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania",
        	    "Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta",
        	    "Marshall Islnds","Martinique","Mauretania","Mauritius","Mayotte","Mexico","Micronesia","Minor Outl.Isl.",
        	    "Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","N.Mariana Islnd",
        	    "Namibia","Nauru","Nepal","Netherlands","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria",
        	    "Niue","Norfolk Islands","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama",
        	    "Pap. New Guinea","Paraguay","Peru","Philippines","Pitcairn Islnds","Poland","Portugal","Puerto Rico",
        	    "Qatar","Rep.of Congo","Reunion","Romania","Russian Fed.","Rwanda","S. Sandwich Ins","S.Tome,Principe",
        	    "Saint Helena","Samoa","Samoa, America","San Marino","Saudi Arabia","Senegal","Serbia","Serbia/Monten.",
        	    "Seychelles","Sierra Leone","Singapore","Sint Maarten","Slovakia","Slovenia","Solomon Islands","Somalia",
        	    "South Africa","South Korea","Spain","Sri Lanka","St Kitts&Nevis","St. Lucia","St. Vincent","St.Pier,Miquel.",
        	    "Sudan","Suriname","Svalbard","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania",
        	    "Thailand","Togo","Tokelau Islands","Tonga","Trinidad,Tobago","Tunisia","Turkey","Turkmenistan",
        	    "Turksh Caicosin","Tuvalu","Uganda","Ukraine","United Kingdom","Uruguay","USA","Utd.Arab Emir.","Uzbekistan",
        	    "Vanuatu","Vatican City","Venezuela","Vietnam","Wallis,Futuna","West Sahara","Yemen","Zambia","Zimbabwe"};
        JComboBox<String> countryDropdown = new JComboBox<>(countries);

        // Dropdown για browser
        String[] browsers = {"chrome", "firefox", "edge"};
        JComboBox<String> browserDropdown = new JComboBox<>(browsers);

        JButton runButton = new JButton("Τρέξε Tests");

        runButton.addActionListener(e -> {
            String country = (String) countryDropdown.getSelectedItem();
            String browser = (String) browserDropdown.getSelectedItem();

            new Thread(() -> {
                System.setProperty("country", country);
                System.setProperty("browser", browser);

                TestNG testng = new TestNG();
                testng.setTestSuites(Arrays.asList("testng.xml"));
                testng.run();
            }).start();
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Χώρα:"));
        frame.add(countryDropdown);
        frame.add(new JLabel("Browser:"));
        frame.add(browserDropdown);
        frame.add(runButton);

        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}