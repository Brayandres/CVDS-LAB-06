package edu.eci.cvds.servlet.JSF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name = "calculadoraBean")
@SessionScoped

/**
 * 
 * @author Brayan Macias
 *
 */
public class CalculadoraBean {

	private float[] values;
	private int numberOfValues;
	private Hashtable<String, Boolean> wasCalculated;
	private float mean, mode, standardDeviation, variance;

	public CalculadoraBean() {
		wasCalculated = new Hashtable<String, Boolean>();
		wasCalculated.put("mean", false);
		wasCalculated.put("mode", false);
		wasCalculated.put("standardDeviation", false);
		wasCalculated.put("variance", false);
	}

	public void setValues(String input) {
		List<String> numbers;
		try {
			numbers = new ArrayList<String>(Arrays.asList(input.split(", ")));
			values = new float[numbers.size()];
			for (int i = 0; i < numbers.size(); i++) {
				values[i] = Integer.parseInt(numbers.get(i));
				calculateMean(values);
				calculateMode(values);
				calculateVariance(values);
				calculateStandardDeviation(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float calculateMean(float[] numbers) {
		if (!wasCalculated.get("mean")) {
			mean = 0;
			for (float number : numbers) {
				mean += number;
			}
			mean /= numbers.length;
			wasCalculated.put("mean", true);
		}
		return mean;
	}

	public float calculateStandardDeviation(float[] numbers) {
		calculateVariance(numbers);
		if (!wasCalculated.get("standardDeviation")) {
			standardDeviation = (float) Math.sqrt(mean);
			wasCalculated.put("standardDeviation", true);
		}
		return standardDeviation;
	}

	public float calculateVariance(float[] numbers) {
		if (!wasCalculated.get("variance")) {
			calculateMean(numbers);
			int i = 0;
			variance = 0;
			for (float number : numbers) {
				variance += (float) Math.pow((number - mean), 2);
				i += 1;
			}
			variance /= numbers.length;
			wasCalculated.put("variance", true);
		}
		return variance;
	}

	public float calculateMode(float[] numbers) {
		if (!wasCalculated.get("mode")) {
			mode = numbers[0];
			float currentValue = numbers[0];
			int currentQty = 0, biggerQty = 0;
			Arrays.sort(numbers);
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i] == currentValue) {
					currentQty += 1;
				} else {
					biggerQty = (currentQty > biggerQty) ? currentQty : biggerQty;
					mode = (currentQty > biggerQty) ? currentValue : mode;
					currentValue = numbers[i];
					currentQty = 1;
				}
			}
			wasCalculated.put("mode", true);
		}
		return mode;
	}

	public void restart() {
		wasCalculated = new Hashtable<String, Boolean>();
		wasCalculated.put("mean", false);
		wasCalculated.put("mode", false);
		wasCalculated.put("standardDeviation", false);
		wasCalculated.put("variance", false);
		float mean, mode, standardDeviation, variance;
		int numberOfValues;
		float[] values;
	}

	public void setValues(float[] values) {
		this.values = values;
	}

	public void setNumberOfValues(int n) {
		this.numberOfValues = n;
	}

	public float[] getValues() {
		return values;
	}

	public int getNumberOfValues() {
		return numberOfValues;
	}

	public float getMean() {
		return calculateMean(values);
	}

	public float getMode() {
		return calculateMode(values);
	}

	public float getVariance() {
		return calculateVariance(values);
	}

	public float getStandardDeviation() {
		return calculateStandardDeviation(values);
	}
}