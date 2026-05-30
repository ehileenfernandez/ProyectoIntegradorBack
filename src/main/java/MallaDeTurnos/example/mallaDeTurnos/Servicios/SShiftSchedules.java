package MallaDeTurnos.example.mallaDeTurnos.Servicios;

import MallaDeTurnos.example.mallaDeTurnos.DTO.DTOShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.DTO.ShiftScheduleResponse;
import MallaDeTurnos.example.mallaDeTurnos.Mappers.ShiftSchedulesMapper;
import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.Repositorio.IShiftSchedules;
import MallaDeTurnos.example.mallaDeTurnos.Utilidades.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SShiftSchedules {
    @Autowired
    IShiftSchedules iShiftSchedules;
    @Autowired
    ShiftSchedulesMapper shiftSchedulesMapper;
    @Autowired
    EntityResolver entityResolver;

    //Constructor


    public SShiftSchedules(EntityResolver entityResolver, IShiftSchedules iShiftSchedules, ShiftSchedulesMapper shiftSchedulesMapper) {
        this.entityResolver = entityResolver;
        this.iShiftSchedules = iShiftSchedules;
        this.shiftSchedulesMapper = shiftSchedulesMapper;
    }

    //Obtengo la fecha actual para saber si estoy en la semana actual
    LocalDate todaysDate = LocalDate.now();

    public List<ShiftScheduleResponse> addNewShiftScheduleResponse(List<Integer> usersIds
            ,List<ShiftScheduleResponse> response
            ,List<MShiftSchedules> shiftSchedules
            ,String statusResponse){
        for (Integer userId:usersIds){
            List<MShiftSchedules> userShifts = shiftSchedules.stream()
                    .filter(s->s.getUserId().equals(userId))
                    .toList();
            if (!userShifts.isEmpty()){
                response.add(new ShiftScheduleResponse(userShifts,statusResponse,userId));
            }
        }
        return response;

    }

    //Obtener turnos cargados simples para verificar si no se cruzan con nuevos
    public List<MShiftSchedules>getSavedShiftSchedules(List<Integer> usersIds,LocalDate mondayDate,LocalDate sundayDate) throws Exception{
        try {
            return iShiftSchedules.findByMUser_UserIdInAndDateBetween(usersIds,mondayDate,sundayDate);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Obtener los turnos cargados, en caso que no haya turnos, revisar semana anterior para verificar
    //si hay turnos, antes de retornar vacio
    public List<ShiftScheduleResponse> getShiftSchedules(List<Integer> usersIds, LocalDate mondayDate, LocalDate sundayDate) throws Exception{
        try{
            List<ShiftScheduleResponse> historicalShiftSchedules = new ArrayList<>();
            List<MShiftSchedules> currentShiftSchedules = iShiftSchedules.findByMUser_UserIdInAndDateBetween(usersIds,mondayDate,sundayDate);

            if (todaysDate.isAfter(sundayDate)){
                    //Enviar turnos como estan
                    historicalShiftSchedules  = addNewShiftScheduleResponse(usersIds,historicalShiftSchedules,currentShiftSchedules,"HISTORICAL");
                    return historicalShiftSchedules;
            } else {
                //Buscar usuarios con turno
                List<Integer> usersWithShift = currentShiftSchedules.stream()
                        .map(MShiftSchedules::getUserId)
                        .distinct()
                        .toList();
                // Obtener usuarios sin turno
                List<Integer> usersWithoutShift = usersIds.stream()
                        .filter(id->!usersWithShift.contains(id))
                        .toList();

                if (usersWithoutShift.isEmpty()){
                    List<ShiftScheduleResponse> currentShiftSchedulesResponse = new ArrayList<>();
                    currentShiftSchedulesResponse = addNewShiftScheduleResponse(usersIds,currentShiftSchedulesResponse,currentShiftSchedules,"CURRENT");
                    return currentShiftSchedulesResponse;
                } else {
                    //Busco en la semana anterior
                    LocalDate lastMonday = mondayDate.minusDays(7);
                    LocalDate lastSunday = sundayDate.minusDays(7);
                    List<MShiftSchedules> lastShiftSchedules = iShiftSchedules.findByMUser_UserIdInAndDateBetween(usersWithoutShift,lastMonday,lastSunday);
                    List<ShiftScheduleResponse> lastShiftSchedulesResponse = new ArrayList<>();
                    List<ShiftScheduleResponse> currentShiftSchedulesResponse = new ArrayList<>();
                    currentShiftSchedulesResponse = addNewShiftScheduleResponse(usersIds,currentShiftSchedulesResponse,currentShiftSchedules,"CURRENT");
                    if(lastShiftSchedules.isEmpty()){
                        return currentShiftSchedulesResponse;
                    } else {
                        lastShiftSchedulesResponse = addNewShiftScheduleResponse(usersWithoutShift,lastShiftSchedulesResponse,lastShiftSchedules,"PREVIOUS");
                        //Combinar las respuestas
                        List<ShiftScheduleResponse> totalShiftSchedulesResponse = new ArrayList<>();
                        totalShiftSchedulesResponse.addAll(currentShiftSchedulesResponse);
                        totalShiftSchedulesResponse.addAll(lastShiftSchedulesResponse);
                        return totalShiftSchedulesResponse;
                    }
                }
            }
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Agregar nuevos horarios
    public List<MShiftSchedules> addNewShiftSchedules(List<DTOShiftSchedules> dtoShiftSchedules) throws Exception{
        try{
            List<MShiftSchedules> mShiftSchedules = dtoShiftSchedules.stream()
                    .map(dtoSS->{
                        try {
                            return entityResolver.resolveShiftSchedule(dtoSS);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();
            //Antes de guardar verificamos si no hay horarios guardados en esa semana de los que recibimos

            List<Integer> usersIds = mShiftSchedules.stream()
                    .map(MShiftSchedules::getUserId)
                    .toList();
            LocalDate mondayDate = mShiftSchedules.stream()
                    .map(MShiftSchedules::getDate)
                    .min(Comparator.naturalOrder())
                    .orElseThrow(()-> new Exception("No hay fecha minima"));
            LocalDate sundayDate = mShiftSchedules.stream()
                    .map(MShiftSchedules::getDate)
                    .max(Comparator.naturalOrder())
                    .orElseThrow(()->new Exception("No hay fecha maxima"));

            List<MShiftSchedules> existingShiftSchedules = getSavedShiftSchedules(usersIds,mondayDate,sundayDate);

            if(existingShiftSchedules.isEmpty()){
                return iShiftSchedules.saveAll(mShiftSchedules);
            }

            List<Integer> existingUsersIds = existingShiftSchedules.stream()
                    .map(MShiftSchedules::getUserId)
                    .toList();
            List<MShiftSchedules> newShiftSchedules = mShiftSchedules.stream()
                    .filter(ss->!existingUsersIds.contains(ss.getUserId()))
                    .toList();
            List<MShiftSchedules> savedShiftSchedules = mShiftSchedules.stream()
                    .filter(ss->existingUsersIds.contains(ss.getUserId()))
                    .toList();
            List<Integer> existingShiftIds = existingShiftSchedules.stream()
                    .map(MShiftSchedules::getShiftId)
                    .toList();

            List<DTOShiftSchedules> dtoSavedShiftSchedules = savedShiftSchedules.stream()
                            .map(shiftSchedulesMapper::toDTO)
                            .toList();

            updateShiftSchedules(existingShiftIds,dtoSavedShiftSchedules);
            return iShiftSchedules.saveAll(newShiftSchedules);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
    //Actualizar horarios
    public List<MShiftSchedules> updateShiftSchedules(List<Integer> shiftIds, List<DTOShiftSchedules> dtoShiftSchedules) throws Exception{
        try{
            List<MShiftSchedules> mShiftSchedules = dtoShiftSchedules.stream()
                    .map(dtoSS->{
                        try {
                            return entityResolver.resolveShiftSchedule(dtoSS);
                        } catch (Exception error) {
                            throw new RuntimeException(error.getMessage());
                        }
                    })
                    .toList();
            List<MShiftSchedules> findShiftSchedules = iShiftSchedules.findAllById(shiftIds);
            if (!findShiftSchedules.isEmpty()){
                //Actualizar registros
                //Recorrer lista para actualizar registro por registro
                for(int i=0;i<findShiftSchedules.size();i++){
                    MShiftSchedules currentShiftSchedule = findShiftSchedules.get(i);
                    MShiftSchedules updatedShiftSchedule = mShiftSchedules.get(i);
                    //Actualizar registros
                    currentShiftSchedule.setStartShift(updatedShiftSchedule.getStartShift());
                    currentShiftSchedule.setEndShift(updatedShiftSchedule.getEndShift());
                    currentShiftSchedule.setBreak1(updatedShiftSchedule.getBreak1());
                    currentShiftSchedule.setBreak2(updatedShiftSchedule.getBreak2());
                    currentShiftSchedule.setLunch(updatedShiftSchedule.getLunch());
                    currentShiftSchedule.setDuration(updatedShiftSchedule.getDuration());
                    currentShiftSchedule.setmCampaigns(updatedShiftSchedule.getmCampaigns());
                    currentShiftSchedule.setTeamLeader(updatedShiftSchedule.getTeamLeader());
                }
                //Guardar turnos
                return iShiftSchedules.saveAll(findShiftSchedules);
            } else throw new Exception("No se encuentran los turnos");
        } catch (Exception error) {
            throw new Exception(error);
        }
    }

    //Buscar todos los turnos guardados para ver historicos, usuarios activos o no
    public List<MShiftSchedules> getHistoricalShiftSchedules (List<Integer> usersIds,LocalDate mondayDate,LocalDate sundayDate) throws Exception{
        try{
            return iShiftSchedules.findByMUser_UserIdInAndDateBetween(usersIds,mondayDate,sundayDate);
        } catch (Exception error) {
            throw new Exception(error);
        }
    }
}
