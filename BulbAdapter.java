public class BulbAdapter implements SmartDevice {
    private final LegacyBulb bulb;
    private static final int K = 4;

    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("Bulb cannot be null");
        }
        this.bulb = bulb;
    }

    @Override
    public void turnOn() {
        bulb.setBrightness(255);
    }

    @Override
    public void turnOff() {
        bulb.setBrightness(0);
    }

    @Override
    public boolean isOn() {
        if (!bulb.hasPower()) {
            return false;
        }
        return bulb.readBrightness() > 0;
    }

    @Override
    public int getPowerPercent() {
        if (!bulb.hasPower()) {
            return 0;
        }

        int raw = bulb.readBrightness();
        if (raw == 0) {
            return 0;
        }

        int percent = (raw * 100) / 255 + K;

        if (percent > 100) {
            return 100;
        }
        return percent;
    }
}