/**
 * Утилитарный класс для представления точки на игровом поле (2D-пространство).
 * Используется для определения позиции юнитов, маркеров целей и элементов ландшафта.
 *
 * Реализует математику для работы с пространством:
 * - Хранение координат X и Y (в дюймах).
 * - Расчет евклидова расстояния (дистанции) между двумя точками через теорему Пифагора.
 *
 * Необходим для реализации механик Movement (проверка дальности хода),
 * Shooting (проверка дальности оружия) и Charge (проверка дистанции нападения).
 */

package ru.smiras.warhammer40k.core.util;

public class Coordinates {
    private double x;
    private double y;

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Coordinates other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();

        return Math.sqrt(dx*dx + dy*dy);
    }
}
